USE DWVENTAS_FINAL
GO

IF OBJECT_ID('dw.usp_Reset_DWVENTAS_FINAL') IS NOT NULL
    DROP PROCEDURE dw.usp_Reset_DWVENTAS_FINAL
GO

CREATE PROCEDURE dw.usp_Reset_DWVENTAS_FINAL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SQL NVARCHAR(MAX) = '';

    ---------------------------------------------------
    -- 1️⃣ ASEGURAR ESQUEMAS
    ---------------------------------------------------
    IF NOT EXISTS (SELECT 1 FROM sys.schemas WHERE name='stg')
        EXEC('CREATE SCHEMA stg AUTHORIZATION dbo;');

    IF NOT EXISTS (SELECT 1 FROM sys.schemas WHERE name='dw')
        EXEC('CREATE SCHEMA dw AUTHORIZATION dbo;');

    ---------------------------------------------------
    -- 2️⃣ TABLAS EXCLUIDAS
    ---------------------------------------------------
    DECLARE @TablasExcluidas TABLE (
        SchemaName SYSNAME,
        TableName SYSNAME
    );

    INSERT INTO @TablasExcluidas VALUES
        ('dw','DimTipoEvento'),
        ('dw','ETL_Control');

    ---------------------------------------------------
    -- 3️⃣ CREAR TABLAS SI NO EXISTEN
    ---------------------------------------------------

    -- STG FACTURA
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

    -- DIM FECHA
    IF OBJECT_ID('dw.DimFecha','U') IS NULL
    BEGIN
        CREATE TABLE dw.DimFecha(
            FechaKey INT PRIMARY KEY,
            Fecha DATE NOT NULL UNIQUE,
            Anio SMALLINT NOT NULL,
            Mes TINYINT NOT NULL,
            NombreMes VARCHAR(15) NOT NULL,
            Trimestre TINYINT NOT NULL,
            Dia TINYINT NOT NULL,
            DiaSemana TINYINT NOT NULL,
            NombreDia VARCHAR(15) NOT NULL
        );
    END

    -- FACT VENTAS LINEA
    IF OBJECT_ID('dw.FactVentasLinea','U') IS NULL
    BEGIN
        CREATE TABLE dw.FactVentasLinea(
            NumFactura VARCHAR(12) NOT NULL,
            FechaFacturaKey INT NOT NULL,
            FechaCancelacionKey INT NULL,
            ClienteKey INT NOT NULL,
            ProductoKey INT NOT NULL,
            VendedorKey INT NOT NULL,
            DistritoClienteKey INT NOT NULL,
            DistritoVendedorKey INT NOT NULL,
            EstadoFactura VARCHAR(10) NOT NULL,
            PorIGV DECIMAL(10,4) NOT NULL,
            Cantidad INT NOT NULL,
            PrecioVentaUnit MONEY NOT NULL,
            ImporteBruto MONEY NOT NULL,
            MontoIGV MONEY NOT NULL,
            ImporteNeto MONEY NOT NULL,
            CostoUnitarioBase MONEY NOT NULL,
            ImporteCosto MONEY NOT NULL,
            Margen MONEY NOT NULL,
            CONSTRAINT PK_FVL PRIMARY KEY (NumFactura, ProductoKey)
        );
    END

    ---------------------------------------------------
    -- 4️⃣ RESET CONTROLADO (solo tablas no excluidas)
    ---------------------------------------------------

    -- Deshabilitar constraints
    SELECT @SQL = @SQL + 
        'ALTER TABLE [' + s.name + '].[' + t.name + '] NOCHECK CONSTRAINT ALL;' + CHAR(13)
    FROM sys.tables t
    INNER JOIN sys.schemas s ON t.schema_id = s.schema_id
    WHERE s.name IN ('dw','stg')
    AND NOT EXISTS (
        SELECT 1 FROM @TablasExcluidas e
        WHERE e.SchemaName = s.name
        AND e.TableName = t.name
    );

    EXEC sp_executesql @SQL;
    SET @SQL = '';

    ---------------------------------------------------
    -- BORRADO
    ---------------------------------------------------
    SELECT @SQL = @SQL + 
        'DELETE FROM [' + s.name + '].[' + t.name + '];' + CHAR(13)
    FROM sys.tables t
    INNER JOIN sys.schemas s ON t.schema_id = s.schema_id
    WHERE s.name IN ('dw','stg')
    AND NOT EXISTS (
        SELECT 1 FROM @TablasExcluidas e
        WHERE e.SchemaName = s.name
        AND e.TableName = t.name
    )
    ORDER BY s.name DESC;

    EXEC sp_executesql @SQL;
    SET @SQL = '';

    ---------------------------------------------------
    -- Rehabilitar constraints
    ---------------------------------------------------
    SELECT @SQL = @SQL + 
        'ALTER TABLE [' + s.name + '].[' + t.name + '] WITH CHECK CHECK CONSTRAINT ALL;' + CHAR(13)
    FROM sys.tables t
    INNER JOIN sys.schemas s ON t.schema_id = s.schema_id
    WHERE s.name IN ('dw','stg')
    AND NOT EXISTS (
        SELECT 1 FROM @TablasExcluidas e
        WHERE e.SchemaName = s.name
        AND e.TableName = t.name
    );

    EXEC sp_executesql @SQL;

END
GO