/*=====================================================================
  DWVENTAS_FINAL (SQL Server 2019) - SCRIPT ÚNICO CONSOLIDADO (FORMAL)
  --------------------------------------------------------------------
  Integra con OLTP Audit Journal:
    BDVENTAS.aud.AUD_CAMBIOS (AuditId incremental)

  MODOS:
    A) FULL INIT : snapshot total (primer carga) + eventos full
    B) DELTA     : incremental real por AuditId (I/U/D)

  CARACTERÍSTICAS:
    1) Staging formal por lote (BatchId)
    2) Auditoría por pasos (stg.ETL_AUDIT)
    3) DQ (full y delta)
    4) Dimensiones:
        - DimDistrito: SCD1 + baja lógica (si OLTP borra distrito)
        - DimCliente/Producto/Vendedor: SCD2 + soft delete (si OLTP borra)
        - Unknown (late arriving)
    5) Hechos:
        - FactVentasLinea: UPSERT por facturas afectadas (delta)
        - FactEventosVentas: incremental por facturas afectadas (delta)
    6) Control:
        - dw.ETL_Control.UltimoAuditId = watermark único incremental

  Regla de anulación:
    EST_FAC='3' => evento ANULACION (signo negativo)

  OLTP: BDVENTAS
  DW  : DWVENTAS_FINAL
=====================================================================*/
--Creación de la base de datos 
USE MASTER;
GO
-- Forzar el cierre de todas las conexiones activas
ALTER DATABASE DWVENTAS_FINAL SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO
-- Eliminar la base de datos
DROP DATABASE DWVENTAS_FINAL;
GO



------------------------------------------------------------
-- 0) CREAR / USAR BD
------------------------------------------------------------
IF DB_ID('DWVENTAS_FINAL') IS NULL
    CREATE DATABASE DWVENTAS_FINAL;
GO
USE DWVENTAS_FINAL;
GO

------------------------------------------------------------
-- 1) ESQUEMAS
------------------------------------------------------------
IF NOT EXISTS (SELECT 1 FROM sys.schemas WHERE name='stg')
    EXEC('CREATE SCHEMA stg AUTHORIZATION dbo;');
GO
IF NOT EXISTS (SELECT 1 FROM sys.schemas WHERE name='dw')
    EXEC('CREATE SCHEMA dw AUTHORIZATION dbo;');
GO

/*============================================================
  2) TABLAS (crear si no existen)
  - No hacemos DROP masivo para no destruir DW en producción.
  - Si quieres reinicio total: hazlo manualmente (laboratorio).
============================================================*/

------------------------------------------------------------
-- 2.1 AUDITORÍA ETL
------------------------------------------------------------
IF OBJECT_ID('stg.ETL_AUDIT','U') IS NULL
BEGIN
    CREATE TABLE stg.ETL_AUDIT(
        AuditId     BIGINT IDENTITY(1,1) PRIMARY KEY,
        BatchId     BIGINT NOT NULL,
        Proceso     VARCHAR(50) NOT NULL,
        Paso        VARCHAR(50) NOT NULL,
        Inicio      DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        Fin         DATETIME2 NULL,
        Filas       INT NULL,
        Estado      VARCHAR(20) NOT NULL DEFAULT 'RUNNING',
        Mensaje     NVARCHAR(4000) NULL
    );
END
GO

------------------------------------------------------------
-- 2.2 CONTROL (watermark formal: UltimoAuditId)
------------------------------------------------------------
IF OBJECT_ID('dw.ETL_Control','U') IS NULL
BEGIN
    CREATE TABLE dw.ETL_Control(
        Proceso         VARCHAR(50) NOT NULL PRIMARY KEY,
        UltimoAuditId   BIGINT NULL,
        UltimaEjecucion DATETIME2 NULL,
        Estado          VARCHAR(20) NULL,
        Mensaje         NVARCHAR(4000) NULL
    );

    INSERT INTO dw.ETL_Control(Proceso, UltimoAuditId, Estado)
    VALUES ('VENTAS', 0, 'NUEVO');
END
GO

-- Normalizar si existe pero UltimoAuditId está NULL
UPDATE dw.ETL_Control
   SET UltimoAuditId = 0
 WHERE Proceso='VENTAS'
   AND UltimoAuditId IS NULL;
GO

------------------------------------------------------------
-- 2.3 STAGING (snapshot por lote)
------------------------------------------------------------
IF OBJECT_ID('stg.stg_DISTRITO','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_DISTRITO(
        BatchId BIGINT NOT NULL,
        LoadDtm DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        COD_DIS CHAR(5) NOT NULL,
        NOM_DIS VARCHAR(50) NOT NULL
    );
END
GO

IF OBJECT_ID('stg.stg_CLIENTE','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_CLIENTE(
        BatchId BIGINT NOT NULL,
        LoadDtm DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        COD_CLI CHAR(5) NOT NULL,
        RSO_CLI CHAR(30) NOT NULL,
        DIR_CLI VARCHAR(100) NOT NULL,
        TLF_CLI CHAR(9) NOT NULL,
        RUC_CLI CHAR(11) NULL,
        COD_DIS CHAR(5) NOT NULL,
        FEC_REG DATE NOT NULL,
        TIP_CLI VARCHAR(10) NOT NULL,
        CON_CLI VARCHAR(30) NOT NULL
    );
END
GO

IF OBJECT_ID('stg.stg_PRODUCTO','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_PRODUCTO(
        BatchId BIGINT NOT NULL,
        LoadDtm DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        COD_PRO CHAR(5) NOT NULL,
        DES_PRO VARCHAR(50) NOT NULL,
        PRE_PRO MONEY NOT NULL,
        UNI_PRO VARCHAR(30) NOT NULL,
        LIN_PRO VARCHAR(30) NOT NULL,
        IMP_PRO VARCHAR(10) NOT NULL
    );
END
GO

IF OBJECT_ID('stg.stg_VENDEDOR','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_VENDEDOR(
        BatchId BIGINT NOT NULL,
        LoadDtm DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        COD_VEN CHAR(3) NOT NULL,
        NOM_VEN VARCHAR(20) NOT NULL,
        APE_VEN VARCHAR(20) NOT NULL,
        SUE_VEN MONEY NOT NULL,
        FIN_VEN DATE NOT NULL,
        TIP_VEN VARCHAR(10) NOT NULL,
        COD_DIS CHAR(5) NOT NULL
    );
END
GO

IF OBJECT_ID('stg.stg_FACTURA','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_FACTURA(
        BatchId BIGINT NOT NULL,
        LoadDtm DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        NUM_FAC VARCHAR(12) NOT NULL,
        FEC_FAC DATE NOT NULL,
        COD_CLI CHAR(5) NOT NULL,
        FEC_CAN DATE NULL,
        EST_FAC VARCHAR(10) NOT NULL,
        COD_VEN CHAR(3) NOT NULL,
        POR_IGV DECIMAL(10,4) NOT NULL
    );
END
GO

IF OBJECT_ID('stg.stg_DETALLE_FACTURA','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_DETALLE_FACTURA(
        BatchId BIGINT NOT NULL,
        LoadDtm DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        NUM_FAC VARCHAR(12) NOT NULL,
        COD_PRO CHAR(5) NOT NULL,
        CAN_VEN INT NOT NULL,
        PRE_VEN MONEY NOT NULL
    );
END
GO

-- Deletes detectados (dim y distrito)
IF OBJECT_ID('stg.stg_Deletes','U') IS NULL
BEGIN
    CREATE TABLE stg.stg_Deletes(
        BatchId     BIGINT NOT NULL,
        LoadDtm     DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
        Tabla       SYSNAME NOT NULL,     -- CLIENTE/PRODUCTO/VENDEDOR/DISTRITO
        Operacion   CHAR(1) NOT NULL,     -- 'D'
        BusinessKey VARCHAR(200) NOT NULL -- 'COD_CLI=C001', etc.
    );
    CREATE INDEX IX_stg_Deletes_Batch ON stg.stg_Deletes(BatchId, Tabla);
END
GO

------------------------------------------------------------
-- 2.4 DIMENSIONES
------------------------------------------------------------
IF OBJECT_ID('dw.DimFecha','U') IS NULL
BEGIN
    CREATE TABLE dw.DimFecha(
        FechaKey     INT NOT NULL PRIMARY KEY,
        Fecha        DATE NOT NULL UNIQUE,
        Anio         SMALLINT NOT NULL,
        Mes          TINYINT NOT NULL,
        NombreMes    VARCHAR(15) NOT NULL,
        Trimestre    TINYINT NOT NULL,
        Dia          TINYINT NOT NULL,
        DiaSemana    TINYINT NOT NULL,
        NombreDia    VARCHAR(15) NOT NULL
    );
END
GO

IF OBJECT_ID('dw.DimDistrito','U') IS NULL
BEGIN
    CREATE TABLE dw.DimDistrito(
        DistritoKey INT IDENTITY(1,1) PRIMARY KEY,
        COD_DIS     CHAR(5) NOT NULL UNIQUE,
        NOM_DIS     VARCHAR(50) NOT NULL,
        Activo      BIT NOT NULL CONSTRAINT DF_DimDistrito_Activo DEFAULT(1),
        FechaBaja   DATE NULL
    );
END
GO

IF OBJECT_ID('dw.DimCliente','U') IS NULL
BEGIN
    CREATE TABLE dw.DimCliente(
        ClienteKey  INT IDENTITY(1,1) PRIMARY KEY,
        COD_CLI     CHAR(5) NOT NULL,
        RSO_CLI     CHAR(30) NOT NULL,
        DIR_CLI     VARCHAR(100) NOT NULL,
        TLF_CLI     CHAR(9) NOT NULL,
        RUC_CLI     CHAR(11) NULL,
        TIP_CLI     VARCHAR(10) NOT NULL,
        CON_CLI     VARCHAR(30) NOT NULL,
        FEC_REG     DATE NOT NULL,
        COD_DIS     CHAR(5) NOT NULL,
        FechaInicio DATE NOT NULL,
        FechaFin    DATE NOT NULL,
        Activo      BIT NOT NULL,
        CONSTRAINT UQ_DimCliente_SCD UNIQUE (COD_CLI, FechaInicio)
    );
    CREATE INDEX IX_DimCliente_CodActivo ON dw.DimCliente(COD_CLI, Activo);
END
GO

IF OBJECT_ID('dw.DimProducto','U') IS NULL
BEGIN
    CREATE TABLE dw.DimProducto(
        ProductoKey INT IDENTITY(1,1) PRIMARY KEY,
        COD_PRO     CHAR(5) NOT NULL,
        DES_PRO     VARCHAR(50) NOT NULL,
        PRE_PRO     MONEY NOT NULL,
        UNI_PRO     VARCHAR(30) NOT NULL,
        LIN_PRO     VARCHAR(30) NOT NULL,
        IMP_PRO     VARCHAR(10) NOT NULL,
        FechaInicio DATE NOT NULL,
        FechaFin    DATE NOT NULL,
        Activo      BIT NOT NULL,
        CONSTRAINT UQ_DimProducto_SCD UNIQUE (COD_PRO, FechaInicio)
    );
    CREATE INDEX IX_DimProducto_CodActivo ON dw.DimProducto(COD_PRO, Activo);
END
GO

IF OBJECT_ID('dw.DimVendedor','U') IS NULL
BEGIN
    CREATE TABLE dw.DimVendedor(
        VendedorKey INT IDENTITY(1,1) PRIMARY KEY,
        COD_VEN     CHAR(3) NOT NULL,
        NOM_VEN     VARCHAR(20) NOT NULL,
        APE_VEN     VARCHAR(20) NOT NULL,
        SUE_VEN     MONEY NOT NULL,
        FIN_VEN     DATE NOT NULL,
        TIP_VEN     VARCHAR(10) NOT NULL,
        COD_DIS     CHAR(5) NOT NULL,
        FechaInicio DATE NOT NULL,
        FechaFin    DATE NOT NULL,
        Activo      BIT NOT NULL,
        CONSTRAINT UQ_DimVendedor_SCD UNIQUE (COD_VEN, FechaInicio)
    );
    CREATE INDEX IX_DimVendedor_CodActivo ON dw.DimVendedor(COD_VEN, Activo);
END
GO

IF OBJECT_ID('dw.DimTipoEvento','U') IS NULL
BEGIN
    CREATE TABLE dw.DimTipoEvento(
        TipoEventoKey INT IDENTITY(1,1) PRIMARY KEY,
        CodigoEvento  VARCHAR(20) NOT NULL UNIQUE,
        Signo         SMALLINT NOT NULL
    );

    INSERT INTO dw.DimTipoEvento(CodigoEvento,Signo)
    VALUES ('VENTA',+1),('ANULACION',-1);
END
GO

------------------------------------------------------------
-- 2.5 HECHOS
------------------------------------------------------------
IF OBJECT_ID('dw.FactVentasLinea','U') IS NULL
BEGIN
    CREATE TABLE dw.FactVentasLinea(
        NumFactura            VARCHAR(12) NOT NULL,
        FechaFacturaKey       INT NOT NULL,
        FechaCancelacionKey   INT NULL,
        ClienteKey            INT NOT NULL,
        ProductoKey           INT NOT NULL,
        VendedorKey           INT NOT NULL,
        DistritoClienteKey    INT NOT NULL,
        DistritoVendedorKey   INT NOT NULL,
        EstadoFactura         VARCHAR(10) NOT NULL,
        PorIGV                DECIMAL(10,4) NOT NULL,
        Cantidad              INT NOT NULL,
        PrecioVentaUnit       MONEY NOT NULL,
        ImporteBruto          MONEY NOT NULL,
        MontoIGV              MONEY NOT NULL,
        ImporteNeto           MONEY NOT NULL,
        CostoUnitarioBase     MONEY NOT NULL,
        ImporteCosto          MONEY NOT NULL,
        Margen                MONEY NOT NULL,
        CONSTRAINT PK_FVL PRIMARY KEY (NumFactura, ProductoKey),
        CONSTRAINT FK_FVL_FechaFac FOREIGN KEY (FechaFacturaKey) REFERENCES dw.DimFecha(FechaKey),
        CONSTRAINT FK_FVL_FechaCan FOREIGN KEY (FechaCancelacionKey) REFERENCES dw.DimFecha(FechaKey),
        CONSTRAINT FK_FVL_Cli FOREIGN KEY (ClienteKey) REFERENCES dw.DimCliente(ClienteKey),
        CONSTRAINT FK_FVL_Pro FOREIGN KEY (ProductoKey) REFERENCES dw.DimProducto(ProductoKey),
        CONSTRAINT FK_FVL_Ven FOREIGN KEY (VendedorKey) REFERENCES dw.DimVendedor(VendedorKey),
        CONSTRAINT FK_FVL_DisCli FOREIGN KEY (DistritoClienteKey) REFERENCES dw.DimDistrito(DistritoKey),
        CONSTRAINT FK_FVL_DisVen FOREIGN KEY (DistritoVendedorKey) REFERENCES dw.DimDistrito(DistritoKey)
    );
END
GO

IF OBJECT_ID('dw.FactEventosVentas','U') IS NULL
BEGIN
    CREATE TABLE dw.FactEventosVentas(
        EventId BIGINT IDENTITY(1,1) PRIMARY KEY,
        FechaEventoKey       INT NOT NULL,
        TipoEventoKey        INT NOT NULL,
        ClienteKey           INT NOT NULL,
        ProductoKey          INT NOT NULL,
        VendedorKey          INT NOT NULL,
        DistritoClienteKey   INT NOT NULL,
        DistritoVendedorKey  INT NOT NULL,
        NumFactura           VARCHAR(12) NOT NULL,
        SecuenciaEvento      INT NOT NULL DEFAULT(1),
        Cantidad             INT NOT NULL,
        ImporteNeto          MONEY NOT NULL,
        ImporteCosto         MONEY NOT NULL,
        Margen               MONEY NOT NULL,
        CONSTRAINT UQ_FEV UNIQUE (NumFactura, ProductoKey, TipoEventoKey, SecuenciaEvento),
        CONSTRAINT FK_FEV_Fecha FOREIGN KEY (FechaEventoKey) REFERENCES dw.DimFecha(FechaKey),
        CONSTRAINT FK_FEV_Tipo  FOREIGN KEY (TipoEventoKey) REFERENCES dw.DimTipoEvento(TipoEventoKey)
    );
END
GO

/*============================================================
  3) UNKNOWN (Late Arriving) - idempotente
============================================================*/
IF NOT EXISTS (SELECT 1 FROM dw.DimDistrito WHERE COD_DIS='D00')
    INSERT INTO dw.DimDistrito(COD_DIS,NOM_DIS,Activo) VALUES('D00','UNKNOWN',1);
GO

IF NOT EXISTS (SELECT 1 FROM dw.DimCliente WHERE COD_CLI='?????')
    INSERT INTO dw.DimCliente(COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,TIP_CLI,CON_CLI,FEC_REG,COD_DIS,FechaInicio,FechaFin,Activo)
    VALUES('?????','UNKNOWN','UNKNOWN','000000000',NULL,'UNKNOWN','UNKNOWN','19000101','D00','19000101','99991231',1);
GO

IF NOT EXISTS (SELECT 1 FROM dw.DimProducto WHERE COD_PRO='?????')
    INSERT INTO dw.DimProducto(COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO,FechaInicio,FechaFin,Activo)
    VALUES('?????','UNKNOWN',0,'UNKNOWN','UNKNOWN','U','19000101','99991231',1);
GO

IF NOT EXISTS (SELECT 1 FROM dw.DimVendedor WHERE COD_VEN='???')
    INSERT INTO dw.DimVendedor(COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS,FechaInicio,FechaFin,Activo)
    VALUES('???','UNKNOWN','UNKNOWN',0,'19000101','UNKNOWN','D00','19000101','99991231',1);
GO

/*============================================================
  4) FUNCIÓN FechaKey
============================================================*/
CREATE OR ALTER FUNCTION dw.fn_FechaKey(@d DATE)
RETURNS INT
AS
BEGIN
    RETURN (YEAR(@d)*10000 + MONTH(@d)*100 + DAY(@d));
END
GO

































