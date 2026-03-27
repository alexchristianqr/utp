/*============================================================
  5) PROCEDURES
============================================================*/

------------------------------------------------------------
-- 5.1 BatchId
------------------------------------------------------------
CREATE OR ALTER PROCEDURE stg.usp_NewBatch
    @BatchId BIGINT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;
    SET @BatchId = CAST(DATEDIFF(SECOND,'20000101',SYSDATETIME()) AS BIGINT);
END
GO

------------------------------------------------------------
-- 5.2 EXTRACT FULL (snapshot total)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE stg.usp_Extract_Ventas_ToSTG_Full
    @BatchId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO stg.ETL_AUDIT(BatchId,Proceso,Paso) VALUES (@BatchId,'VENTAS','EXTRACT_FULL');
    DECLARE @aid BIGINT = SCOPE_IDENTITY();

    INSERT INTO stg.stg_DISTRITO(BatchId,COD_DIS,NOM_DIS)
    SELECT @BatchId,COD_DIS,NOM_DIS FROM BDVENTAS.dbo.DISTRITO;

    INSERT INTO stg.stg_CLIENTE(BatchId,COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,COD_DIS,FEC_REG,TIP_CLI,CON_CLI)
    SELECT @BatchId,COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,COD_DIS,FEC_REG,TIP_CLI,CON_CLI
    FROM BDVENTAS.dbo.CLIENTE;

    INSERT INTO stg.stg_PRODUCTO(BatchId,COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO)
    SELECT @BatchId,COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO
    FROM BDVENTAS.dbo.PRODUCTO;

    INSERT INTO stg.stg_VENDEDOR(BatchId,COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS)
    SELECT @BatchId,COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS
    FROM BDVENTAS.dbo.VENDEDOR;

    INSERT INTO stg.stg_FACTURA(BatchId,NUM_FAC,FEC_FAC,COD_CLI,FEC_CAN,EST_FAC,COD_VEN,POR_IGV)
    SELECT @BatchId,NUM_FAC,FEC_FAC,COD_CLI,FEC_CAN,EST_FAC,COD_VEN,CAST(POR_IGV AS DECIMAL(10,4))
    FROM BDVENTAS.dbo.FACTURA;

    INSERT INTO stg.stg_DETALLE_FACTURA(BatchId,NUM_FAC,COD_PRO,CAN_VEN,PRE_VEN)
    SELECT @BatchId,NUM_FAC,COD_PRO,CAN_VEN,PRE_VEN
    FROM BDVENTAS.dbo.DETALLE_FACTURA;

    UPDATE stg.ETL_AUDIT
       SET Fin=SYSDATETIME(), Estado='OK', Mensaje='Extract full OK'
     WHERE AuditId=@aid;
END
GO

------------------------------------------------------------
-- 5.3 EXTRACT DELTA (por AUD_CAMBIOS)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE stg.usp_Extract_Ventas_ToSTG_Delta
    @BatchId BIGINT,
    @MaxAuditId BIGINT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO stg.ETL_AUDIT(BatchId,Proceso,Paso) VALUES (@BatchId,'VENTAS','EXTRACT_DELTA');
    DECLARE @aid BIGINT = SCOPE_IDENTITY();

    DECLARE @UltAuditId BIGINT;
    SELECT @UltAuditId = UltimoAuditId FROM dw.ETL_Control WHERE Proceso='VENTAS';

    IF OBJECT_ID('tempdb..#Cambios') IS NOT NULL DROP TABLE #Cambios;

    SELECT c.AuditId, c.Tabla, c.Operacion, c.BusinessKey, c.ChangeDtm
    INTO #Cambios
    FROM BDVENTAS.aud.AUD_CAMBIOS c
    WHERE c.AuditId > @UltAuditId;

    SELECT @MaxAuditId = ISNULL(MAX(AuditId), @UltAuditId) FROM #Cambios;

    IF NOT EXISTS (SELECT 1 FROM #Cambios)
    BEGIN
        UPDATE stg.ETL_AUDIT
           SET Fin=SYSDATETIME(), Estado='OK',
               Mensaje=CONCAT('Delta vacío. UltAuditId=',@UltAuditId,' MaxAuditId=',@MaxAuditId)
         WHERE AuditId=@aid;
        RETURN;
    END

    -- Persistir deletes de dimensiones y distrito
    INSERT INTO stg.stg_Deletes(BatchId, Tabla, Operacion, BusinessKey)
    SELECT @BatchId, Tabla, Operacion, BusinessKey
    FROM #Cambios
    WHERE Operacion='D'
      AND Tabla IN ('CLIENTE','PRODUCTO','VENDEDOR','DISTRITO');

    -- BKs
    IF OBJECT_ID('tempdb..#bkCliente')  IS NOT NULL DROP TABLE #bkCliente;
    IF OBJECT_ID('tempdb..#bkProducto') IS NOT NULL DROP TABLE #bkProducto;
    IF OBJECT_ID('tempdb..#bkVendedor') IS NOT NULL DROP TABLE #bkVendedor;
    IF OBJECT_ID('tempdb..#bkDistrito') IS NOT NULL DROP TABLE #bkDistrito;
    IF OBJECT_ID('tempdb..#bkFactura')  IS NOT NULL DROP TABLE #bkFactura;
    IF OBJECT_ID('tempdb..#bkDetalle')  IS NOT NULL DROP TABLE #bkDetalle;

    SELECT DISTINCT REPLACE(BusinessKey,'COD_CLI=','') AS COD_CLI
    INTO #bkCliente
    FROM #Cambios
    WHERE Tabla='CLIENTE' AND BusinessKey LIKE 'COD_CLI=%' AND Operacion IN ('I','U');

    SELECT DISTINCT REPLACE(BusinessKey,'COD_PRO=','') AS COD_PRO
    INTO #bkProducto
    FROM #Cambios
    WHERE Tabla='PRODUCTO' AND BusinessKey LIKE 'COD_PRO=%' AND Operacion IN ('I','U');

    SELECT DISTINCT REPLACE(BusinessKey,'COD_VEN=','') AS COD_VEN
    INTO #bkVendedor
    FROM #Cambios
    WHERE Tabla='VENDEDOR' AND BusinessKey LIKE 'COD_VEN=%' AND Operacion IN ('I','U');

    SELECT DISTINCT REPLACE(BusinessKey,'COD_DIS=','') AS COD_DIS
    INTO #bkDistrito
    FROM #Cambios
    WHERE Tabla='DISTRITO' AND BusinessKey LIKE 'COD_DIS=%' AND Operacion IN ('I','U');

    SELECT DISTINCT REPLACE(BusinessKey,'NUM_FAC=','') AS NUM_FAC
    INTO #bkFactura
    FROM #Cambios
    WHERE Tabla='FACTURA' AND BusinessKey LIKE 'NUM_FAC=%' AND Operacion IN ('I','U','D');

    ;WITH x AS (
        SELECT
            SUBSTRING(BusinessKey,
                      CHARINDEX('NUM_FAC=',BusinessKey)+LEN('NUM_FAC='),
                      CHARINDEX('|COD_PRO=',BusinessKey)-(CHARINDEX('NUM_FAC=',BusinessKey)+LEN('NUM_FAC='))) AS NUM_FAC,
            SUBSTRING(BusinessKey,
                      CHARINDEX('|COD_PRO=',BusinessKey)+LEN('|COD_PRO='),
                      200) AS COD_PRO
        FROM #Cambios
        WHERE Tabla='DETALLE_FACTURA'
          AND BusinessKey LIKE 'NUM_FAC=%|COD_PRO=%'
          AND Operacion IN ('I','U','D')
    )
    SELECT DISTINCT NUM_FAC, COD_PRO
    INTO #bkDetalle
    FROM x;

    /*--- Cargar STG DELTA ---*/

    -- DISTRITO: (a) cambios directos, (b) referencias por clientes/vendedores involucrados
    INSERT INTO stg.stg_DISTRITO(BatchId,COD_DIS,NOM_DIS)
    SELECT DISTINCT @BatchId, d.COD_DIS, d.NOM_DIS
    FROM BDVENTAS.dbo.DISTRITO d
    WHERE d.COD_DIS IN (SELECT COD_DIS FROM #bkDistrito);

    INSERT INTO stg.stg_DISTRITO(BatchId,COD_DIS,NOM_DIS)
    SELECT DISTINCT @BatchId, d.COD_DIS, d.NOM_DIS
    FROM BDVENTAS.dbo.DISTRITO d
    WHERE d.COD_DIS IN (
        SELECT c.COD_DIS FROM BDVENTAS.dbo.CLIENTE c
        WHERE c.COD_CLI IN (SELECT COD_CLI FROM #bkCliente)
           OR c.COD_CLI IN (SELECT f.COD_CLI FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkFactura))
           OR c.COD_CLI IN (SELECT f.COD_CLI FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkDetalle))
        UNION
        SELECT v.COD_DIS FROM BDVENTAS.dbo.VENDEDOR v
        WHERE v.COD_VEN IN (SELECT COD_VEN FROM #bkVendedor)
           OR v.COD_VEN IN (SELECT f.COD_VEN FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkFactura))
           OR v.COD_VEN IN (SELECT f.COD_VEN FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkDetalle))
    );

    -- CLIENTE/VENDEDOR/PRODUCTO: solo filas existentes (deletes se manejan por stg_Deletes)
    INSERT INTO stg.stg_CLIENTE(BatchId,COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,COD_DIS,FEC_REG,TIP_CLI,CON_CLI)
    SELECT DISTINCT @BatchId, c.COD_CLI,c.RSO_CLI,c.DIR_CLI,c.TLF_CLI,c.RUC_CLI,c.COD_DIS,c.FEC_REG,c.TIP_CLI,c.CON_CLI
    FROM BDVENTAS.dbo.CLIENTE c
    WHERE c.COD_CLI IN (SELECT COD_CLI FROM #bkCliente)
       OR c.COD_CLI IN (SELECT f.COD_CLI FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkFactura))
       OR c.COD_CLI IN (SELECT f.COD_CLI FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkDetalle));

    INSERT INTO stg.stg_VENDEDOR(BatchId,COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS)
    SELECT DISTINCT @BatchId, v.COD_VEN,v.NOM_VEN,v.APE_VEN,v.SUE_VEN,v.FIN_VEN,v.TIP_VEN,v.COD_DIS
    FROM BDVENTAS.dbo.VENDEDOR v
    WHERE v.COD_VEN IN (SELECT COD_VEN FROM #bkVendedor)
       OR v.COD_VEN IN (SELECT f.COD_VEN FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkFactura))
       OR v.COD_VEN IN (SELECT f.COD_VEN FROM BDVENTAS.dbo.FACTURA f WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkDetalle));

    INSERT INTO stg.stg_PRODUCTO(BatchId,COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO)
    SELECT DISTINCT @BatchId, p.COD_PRO,p.DES_PRO,p.PRE_PRO,p.UNI_PRO,p.LIN_PRO,p.IMP_PRO
    FROM BDVENTAS.dbo.PRODUCTO p
    WHERE p.COD_PRO IN (SELECT COD_PRO FROM #bkProducto)
       OR p.COD_PRO IN (SELECT COD_PRO FROM #bkDetalle);

    -- FACTURA: (si hay cambios en factura o en detalle)
    INSERT INTO stg.stg_FACTURA(BatchId,NUM_FAC,FEC_FAC,COD_CLI,FEC_CAN,EST_FAC,COD_VEN,POR_IGV)
    SELECT DISTINCT @BatchId, f.NUM_FAC,f.FEC_FAC,f.COD_CLI,f.FEC_CAN,f.EST_FAC,f.COD_VEN,CAST(f.POR_IGV AS DECIMAL(10,4))
    FROM BDVENTAS.dbo.FACTURA f
    WHERE f.NUM_FAC IN (SELECT NUM_FAC FROM #bkFactura)
       OR f.NUM_FAC IN (SELECT NUM_FAC FROM #bkDetalle);

    -- DETALLE: recargar todas las líneas de facturas afectadas
    INSERT INTO stg.stg_DETALLE_FACTURA(BatchId,NUM_FAC,COD_PRO,CAN_VEN,PRE_VEN)
    SELECT DISTINCT @BatchId, df.NUM_FAC,df.COD_PRO,df.CAN_VEN,df.PRE_VEN
    FROM BDVENTAS.dbo.DETALLE_FACTURA df
    WHERE df.NUM_FAC IN (SELECT NUM_FAC FROM #bkFactura)
       OR df.NUM_FAC IN (SELECT NUM_FAC FROM #bkDetalle);

    UPDATE stg.ETL_AUDIT
       SET Fin=SYSDATETIME(), Estado='OK',
           Mensaje=CONCAT('Delta extract OK. UltAuditId=',@UltAuditId,' MaxAuditId=',@MaxAuditId)
     WHERE AuditId=@aid;
END
GO

------------------------------------------------------------
-- 5.4 DQ FULL
------------------------------------------------------------
CREATE OR ALTER PROCEDURE stg.usp_DQ_Validate_Ventas_Full
    @BatchId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO stg.ETL_AUDIT(BatchId,Proceso,Paso) VALUES (@BatchId,'VENTAS','DQ_FULL');
    DECLARE @aid BIGINT = SCOPE_IDENTITY();

    IF EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId GROUP BY NUM_FAC HAVING COUNT(*)>1)
        THROW 70001,'DQ FULL: duplicados en FACTURA (NUM_FAC).',1;

    IF EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId GROUP BY NUM_FAC,COD_PRO HAVING COUNT(*)>1)
        THROW 70002,'DQ FULL: duplicados en DETALLE_FACTURA (NUM_FAC,COD_PRO).',1;

    IF EXISTS (
        SELECT 1 FROM stg.stg_DETALLE_FACTURA df
        LEFT JOIN stg.stg_FACTURA f ON f.BatchId=@BatchId AND f.NUM_FAC=df.NUM_FAC
        WHERE df.BatchId=@BatchId AND f.NUM_FAC IS NULL
    ) THROW 70003,'DQ FULL: detalle sin factura (orphan).',1;

    IF EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId AND (CAN_VEN<=0 OR PRE_VEN<=0))
        THROW 70009,'DQ FULL: CAN_VEN y PRE_VEN deben ser > 0.',1;

    IF EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId AND FEC_CAN IS NOT NULL AND FEC_CAN < FEC_FAC)
        THROW 70010,'DQ FULL: FEC_CAN < FEC_FAC.',1;

    UPDATE stg.ETL_AUDIT SET Fin=SYSDATETIME(), Estado='OK', Mensaje='DQ full OK'
    WHERE AuditId=@aid;
END
GO

------------------------------------------------------------
-- 5.5 DQ DELTA (solo si hay hechos en batch)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE stg.usp_DQ_Validate_Ventas_Delta
    @BatchId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO stg.ETL_AUDIT(BatchId,Proceso,Paso) VALUES (@BatchId,'VENTAS','DQ_DELTA');
    DECLARE @aid BIGINT = SCOPE_IDENTITY();

    IF NOT EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId)
       AND NOT EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId)
    BEGIN
        UPDATE stg.ETL_AUDIT SET Fin=SYSDATETIME(), Estado='OK', Mensaje='DQ delta: sin hechos'
        WHERE AuditId=@aid;
        RETURN;
    END

    IF EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId GROUP BY NUM_FAC,COD_PRO HAVING COUNT(*)>1)
        THROW 70002,'DQ DELTA: duplicados en DETALLE_FACTURA (NUM_FAC,COD_PRO).',1;

    IF EXISTS (
        SELECT 1
        FROM stg.stg_DETALLE_FACTURA df
        LEFT JOIN stg.stg_FACTURA f ON f.BatchId=@BatchId AND f.NUM_FAC=df.NUM_FAC
        WHERE df.BatchId=@BatchId AND f.NUM_FAC IS NULL
    ) THROW 70003,'DQ DELTA: detalle sin factura en batch.',1;

    IF EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId AND (CAN_VEN<=0 OR PRE_VEN<=0))
        THROW 70009,'DQ DELTA: CAN_VEN y PRE_VEN deben ser > 0.',1;

    UPDATE stg.ETL_AUDIT SET Fin=SYSDATETIME(), Estado='OK', Mensaje='DQ delta OK'
    WHERE AuditId=@aid;
END
GO

------------------------------------------------------------
-- 5.6 DimFecha (desde STG)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Load_DimFecha_FromSTG
    @BatchId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    ;WITH Fechas AS (
        SELECT DISTINCT FEC_FAC AS Fecha FROM stg.stg_FACTURA WHERE BatchId=@BatchId
        UNION
        SELECT DISTINCT FEC_CAN AS Fecha FROM stg.stg_FACTURA WHERE BatchId=@BatchId AND FEC_CAN IS NOT NULL
        UNION
        SELECT DISTINCT FEC_REG AS Fecha FROM stg.stg_CLIENTE WHERE BatchId=@BatchId
        UNION
        SELECT DISTINCT FIN_VEN AS Fecha FROM stg.stg_VENDEDOR WHERE BatchId=@BatchId
    )
    INSERT INTO dw.DimFecha(FechaKey,Fecha,Anio,Mes,NombreMes,Trimestre,Dia,DiaSemana,NombreDia)
    SELECT
        dw.fn_FechaKey(Fecha),
        Fecha,
        YEAR(Fecha), MONTH(Fecha), DATENAME(MONTH,Fecha),
        DATEPART(QUARTER,Fecha),
        DAY(Fecha),
        DATEPART(WEEKDAY,Fecha),
        DATENAME(WEEKDAY,Fecha)
    FROM Fechas f
    WHERE Fecha IS NOT NULL
      AND NOT EXISTS (SELECT 1 FROM dw.DimFecha d WHERE d.Fecha=f.Fecha);
END
GO

------------------------------------------------------------
-- 5.7 DimDistrito SCD1 + baja lógica por delete OLTP
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Load_DimDistrito_SCD1
    @BatchId BIGINT,
    @FechaProceso DATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Insert nuevos
    INSERT INTO dw.DimDistrito(COD_DIS,NOM_DIS,Activo,FechaBaja)
    SELECT s.COD_DIS,s.NOM_DIS,1,NULL
    FROM stg.stg_DISTRITO s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimDistrito d WHERE d.COD_DIS=s.COD_DIS);

    -- Update SCD1
    UPDATE d
       SET d.NOM_DIS = s.NOM_DIS,
           d.Activo  = 1,
           d.FechaBaja = NULL
    FROM dw.DimDistrito d
    JOIN stg.stg_DISTRITO s ON s.COD_DIS=d.COD_DIS AND s.BatchId=@BatchId
    WHERE d.NOM_DIS<>s.NOM_DIS OR d.Activo<>1 OR d.FechaBaja IS NOT NULL;

    -- Baja lógica por delete OLTP
    UPDATE d
       SET d.Activo = 0,
           d.FechaBaja = DATEADD(DAY,-1,@FechaProceso)
    FROM dw.DimDistrito d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId
     AND x.Tabla='DISTRITO'
     AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_DIS=', d.COD_DIS)
    WHERE d.Activo=1;
END
GO

------------------------------------------------------------
-- 5.8 SCD2 dims + soft delete (Cliente/Producto/Vendedor)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_SCD2_Load_Dimensions
    @BatchId BIGINT,
    @FechaProceso DATE
AS
BEGIN
    SET NOCOUNT ON;

    /*--- INSERT nuevos (19000101..99991231) ---*/
    INSERT INTO dw.DimProducto(COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO,FechaInicio,FechaFin,Activo)
    SELECT s.COD_PRO,s.DES_PRO,s.PRE_PRO,s.UNI_PRO,s.LIN_PRO,s.IMP_PRO,'19000101','99991231',1
    FROM stg.stg_PRODUCTO s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimProducto d WHERE d.COD_PRO=s.COD_PRO);

    INSERT INTO dw.DimCliente(COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,TIP_CLI,CON_CLI,FEC_REG,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_CLI,s.RSO_CLI,s.DIR_CLI,s.TLF_CLI,s.RUC_CLI,s.TIP_CLI,s.CON_CLI,s.FEC_REG,s.COD_DIS,'19000101','99991231',1
    FROM stg.stg_CLIENTE s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimCliente d WHERE d.COD_CLI=s.COD_CLI);

    INSERT INTO dw.DimVendedor(COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_VEN,s.NOM_VEN,s.APE_VEN,s.SUE_VEN,s.FIN_VEN,s.TIP_VEN,s.COD_DIS,'19000101','99991231',1
    FROM stg.stg_VENDEDOR s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimVendedor d WHERE d.COD_VEN=s.COD_VEN);

    /*--- Cambios: cerrar activo + insertar nueva versión ---*/
    UPDATE d
       SET d.FechaFin=DATEADD(DAY,-1,@FechaProceso), d.Activo=0
    FROM dw.DimProducto d
    JOIN stg.stg_PRODUCTO s ON s.COD_PRO=d.COD_PRO AND s.BatchId=@BatchId
    WHERE d.Activo=1
      AND (d.DES_PRO<>s.DES_PRO OR d.PRE_PRO<>s.PRE_PRO OR d.UNI_PRO<>s.UNI_PRO OR d.LIN_PRO<>s.LIN_PRO OR d.IMP_PRO<>s.IMP_PRO);

    INSERT INTO dw.DimProducto(COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO,FechaInicio,FechaFin,Activo)
    SELECT s.COD_PRO,s.DES_PRO,s.PRE_PRO,s.UNI_PRO,s.LIN_PRO,s.IMP_PRO,@FechaProceso,'99991231',1
    FROM stg.stg_PRODUCTO s
    WHERE s.BatchId=@BatchId
      AND EXISTS (
          SELECT 1 FROM dw.DimProducto d
          WHERE d.COD_PRO=s.COD_PRO AND d.Activo=0 AND d.FechaFin=DATEADD(DAY,-1,@FechaProceso)
      );

    UPDATE d
       SET d.FechaFin=DATEADD(DAY,-1,@FechaProceso), d.Activo=0
    FROM dw.DimCliente d
    JOIN stg.stg_CLIENTE s ON s.COD_CLI=d.COD_CLI AND s.BatchId=@BatchId
    WHERE d.Activo=1
      AND (
            d.RSO_CLI<>s.RSO_CLI OR d.DIR_CLI<>s.DIR_CLI OR d.TLF_CLI<>s.TLF_CLI
         OR ISNULL(d.RUC_CLI,'')<>ISNULL(s.RUC_CLI,'')
         OR d.TIP_CLI<>s.TIP_CLI OR d.CON_CLI<>s.CON_CLI
         OR d.FEC_REG<>s.FEC_REG OR d.COD_DIS<>s.COD_DIS
      );

    INSERT INTO dw.DimCliente(COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,TIP_CLI,CON_CLI,FEC_REG,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_CLI,s.RSO_CLI,s.DIR_CLI,s.TLF_CLI,s.RUC_CLI,s.TIP_CLI,s.CON_CLI,s.FEC_REG,s.COD_DIS,@FechaProceso,'99991231',1
    FROM stg.stg_CLIENTE s
    WHERE s.BatchId=@BatchId
      AND EXISTS (
          SELECT 1 FROM dw.DimCliente d
          WHERE d.COD_CLI=s.COD_CLI AND d.Activo=0 AND d.FechaFin=DATEADD(DAY,-1,@FechaProceso)
      );

    UPDATE d
       SET d.FechaFin=DATEADD(DAY,-1,@FechaProceso), d.Activo=0
    FROM dw.DimVendedor d
    JOIN stg.stg_VENDEDOR s ON s.COD_VEN=d.COD_VEN AND s.BatchId=@BatchId
    WHERE d.Activo=1
      AND (
           d.NOM_VEN<>s.NOM_VEN OR d.APE_VEN<>s.APE_VEN OR d.SUE_VEN<>s.SUE_VEN
        OR d.FIN_VEN<>s.FIN_VEN OR d.TIP_VEN<>s.TIP_VEN OR d.COD_DIS<>s.COD_DIS
      );

    INSERT INTO dw.DimVendedor(COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_VEN,s.NOM_VEN,s.APE_VEN,s.SUE_VEN,s.FIN_VEN,s.TIP_VEN,s.COD_DIS,@FechaProceso,'99991231',1
    FROM stg.stg_VENDEDOR s
    WHERE s.BatchId=@BatchId
      AND EXISTS (
          SELECT 1 FROM dw.DimVendedor d
          WHERE d.COD_VEN=s.COD_VEN AND d.Activo=0 AND d.FechaFin=DATEADD(DAY,-1,@FechaProceso)
      );

    /*--- SOFT DELETE (expirar activo si OLTP borró) ---*/
    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimCliente d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId AND x.Tabla='CLIENTE' AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_CLI=', d.COD_CLI)
    WHERE d.Activo=1;

    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimProducto d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId AND x.Tabla='PRODUCTO' AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_PRO=', d.COD_PRO)
    WHERE d.Activo=1;

    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimVendedor d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId AND x.Tabla='VENDEDOR' AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_VEN=', d.COD_VEN)
    WHERE d.Activo=1;

    /*--- Validación: 1 activa por BK ---*/
    IF EXISTS (SELECT COD_CLI FROM dw.DimCliente WHERE Activo=1 GROUP BY COD_CLI HAVING COUNT(*)>1)
        THROW 71001,'SCD2 ERROR: >1 activa en DimCliente.',1;
    IF EXISTS (SELECT COD_PRO FROM dw.DimProducto WHERE Activo=1 GROUP BY COD_PRO HAVING COUNT(*)>1)
        THROW 71002,'SCD2 ERROR: >1 activa en DimProducto.',1;
    IF EXISTS (SELECT COD_VEN FROM dw.DimVendedor WHERE Activo=1 GROUP BY COD_VEN HAVING COUNT(*)>1)
        THROW 71003,'SCD2 ERROR: >1 activa en DimVendedor.',1;
END
GO

------------------------------------------------------------
-- 5.9 FACT: UPSERT por facturas afectadas (DELTA formal)
--     Estrategia segura: borrar/reinsertar líneas del hecho
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Upsert_FactVentasLinea_FromSTG
    @BatchId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId)
       AND NOT EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId)
        RETURN;

    IF OBJECT_ID('tempdb..#FacturasAfectadas') IS NOT NULL DROP TABLE #FacturasAfectadas;

    SELECT DISTINCT NUM_FAC
    INTO #FacturasAfectadas
    FROM (
        SELECT NUM_FAC FROM stg.stg_FACTURA WHERE BatchId=@BatchId
        UNION
        SELECT NUM_FAC FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId
    ) x;

    -- 1) Borrar líneas existentes del hecho para facturas afectadas
    DELETE f
    FROM dw.FactVentasLinea f
    JOIN #FacturasAfectadas a ON a.NUM_FAC = f.NumFactura;

    -- 2) Reinsertar desde STG (estado actual del OLTP para esas facturas)
    INSERT INTO dw.FactVentasLinea(
        NumFactura,
        FechaFacturaKey, FechaCancelacionKey,
        ClienteKey, ProductoKey, VendedorKey,
        DistritoClienteKey, DistritoVendedorKey,
        EstadoFactura, PorIGV,
        Cantidad, PrecioVentaUnit, ImporteBruto, MontoIGV, ImporteNeto,
        CostoUnitarioBase, ImporteCosto, Margen
    )
    SELECT
        f.NUM_FAC,
        dw.fn_FechaKey(f.FEC_FAC),
        CASE WHEN f.FEC_CAN IS NULL THEN NULL ELSE dw.fn_FechaKey(f.FEC_CAN) END,

        ISNULL(dcli.ClienteKey, (SELECT TOP 1 ClienteKey FROM dw.DimCliente WHERE COD_CLI='?????' AND Activo=1)),
        ISNULL(dpro.ProductoKey, (SELECT TOP 1 ProductoKey FROM dw.DimProducto WHERE COD_PRO='?????' AND Activo=1)),
        ISNULL(dven.VendedorKey, (SELECT TOP 1 VendedorKey FROM dw.DimVendedor WHERE COD_VEN='???' AND Activo=1)),

        ISNULL(ddisCli.DistritoKey, (SELECT TOP 1 DistritoKey FROM dw.DimDistrito WHERE COD_DIS='D00')),
        ISNULL(ddisVen.DistritoKey, (SELECT TOP 1 DistritoKey FROM dw.DimDistrito WHERE COD_DIS='D00')),

        f.EST_FAC,
        f.POR_IGV,

        df.CAN_VEN,
        df.PRE_VEN,
        CAST(df.CAN_VEN * df.PRE_VEN AS MONEY),
        CAST((df.CAN_VEN * df.PRE_VEN) * f.POR_IGV AS MONEY),
        CAST((df.CAN_VEN * df.PRE_VEN) * (1 + f.POR_IGV) AS MONEY),

        ISNULL(dpro.PRE_PRO, 0),
        CAST(df.CAN_VEN * ISNULL(dpro.PRE_PRO,0) AS MONEY),
        CAST((df.CAN_VEN * df.PRE_VEN) - (df.CAN_VEN * ISNULL(dpro.PRE_PRO,0)) AS MONEY)
    FROM stg.stg_FACTURA f
    JOIN #FacturasAfectadas a ON a.NUM_FAC=f.NUM_FAC
    JOIN stg.stg_DETALLE_FACTURA df
      ON df.BatchId=@BatchId AND df.NUM_FAC=f.NUM_FAC

    LEFT JOIN stg.stg_CLIENTE cstg
      ON cstg.BatchId=@BatchId AND cstg.COD_CLI=f.COD_CLI

    LEFT JOIN stg.stg_VENDEDOR vstg
      ON vstg.BatchId=@BatchId AND vstg.COD_VEN=f.COD_VEN

    LEFT JOIN dw.DimCliente dcli
      ON dcli.COD_CLI = f.COD_CLI
     AND f.FEC_FAC BETWEEN dcli.FechaInicio AND dcli.FechaFin

    LEFT JOIN dw.DimProducto dpro
      ON dpro.COD_PRO = df.COD_PRO
     AND f.FEC_FAC BETWEEN dpro.FechaInicio AND dpro.FechaFin

    LEFT JOIN dw.DimVendedor dven
      ON dven.COD_VEN = f.COD_VEN
     AND f.FEC_FAC BETWEEN dven.FechaInicio AND dven.FechaFin

    LEFT JOIN dw.DimDistrito ddisCli ON ddisCli.COD_DIS = ISNULL(cstg.COD_DIS,'D00')
    LEFT JOIN dw.DimDistrito ddisVen ON ddisVen.COD_DIS = ISNULL(vstg.COD_DIS,'D00');
END
GO

------------------------------------------------------------
-- 5.10 EVENTOS incremental por facturas afectadas
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Load_FactEventosVentas_Incremental
    @BatchId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TipoVenta INT = (SELECT TipoEventoKey FROM dw.DimTipoEvento WHERE CodigoEvento='VENTA');
    DECLARE @TipoAnul  INT = (SELECT TipoEventoKey FROM dw.DimTipoEvento WHERE CodigoEvento='ANULACION');

    IF NOT EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId)
       AND NOT EXISTS (SELECT 1 FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId)
        RETURN;

    IF OBJECT_ID('tempdb..#FacturasAfectadas') IS NOT NULL DROP TABLE #FacturasAfectadas;

    SELECT DISTINCT NUM_FAC
    INTO #FacturasAfectadas
    FROM (
        SELECT NUM_FAC FROM stg.stg_FACTURA WHERE BatchId=@BatchId
        UNION
        SELECT NUM_FAC FROM stg.stg_DETALLE_FACTURA WHERE BatchId=@BatchId
    ) x;

    DELETE ev
    FROM dw.FactEventosVentas ev
    JOIN #FacturasAfectadas a ON a.NUM_FAC = ev.NumFactura;

    INSERT INTO dw.FactEventosVentas(
        FechaEventoKey, TipoEventoKey,
        ClienteKey, ProductoKey, VendedorKey, DistritoClienteKey, DistritoVendedorKey,
        NumFactura, SecuenciaEvento,
        Cantidad, ImporteNeto, ImporteCosto, Margen
    )
    SELECT
        f.FechaFacturaKey, @TipoVenta,
        f.ClienteKey, f.ProductoKey, f.VendedorKey, f.DistritoClienteKey, f.DistritoVendedorKey,
        f.NumFactura, 1,
        +f.Cantidad, +f.ImporteNeto, +f.ImporteCosto, +f.Margen
    FROM dw.FactVentasLinea f
    JOIN #FacturasAfectadas a ON a.NUM_FAC=f.NumFactura;

    INSERT INTO dw.FactEventosVentas(
        FechaEventoKey, TipoEventoKey,
        ClienteKey, ProductoKey, VendedorKey, DistritoClienteKey, DistritoVendedorKey,
        NumFactura, SecuenciaEvento,
        Cantidad, ImporteNeto, ImporteCosto, Margen
    )
    SELECT
        ISNULL(f.FechaCancelacionKey, f.FechaFacturaKey), @TipoAnul,
        f.ClienteKey, f.ProductoKey, f.VendedorKey, f.DistritoClienteKey, f.DistritoVendedorKey,
        f.NumFactura, 1,
        -f.Cantidad, -f.ImporteNeto, -f.ImporteCosto, -f.Margen
    FROM dw.FactVentasLinea f
    JOIN #FacturasAfectadas a ON a.NUM_FAC=f.NumFactura
    WHERE f.EstadoFactura='3';
END
GO

------------------------------------------------------------
-- 5.11 Eventos FULL (solo en init)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Load_FactEventosVentas_FromBase
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TipoVenta INT = (SELECT TipoEventoKey FROM dw.DimTipoEvento WHERE CodigoEvento='VENTA');
    DECLARE @TipoAnul  INT = (SELECT TipoEventoKey FROM dw.DimTipoEvento WHERE CodigoEvento='ANULACION');

    TRUNCATE TABLE dw.FactEventosVentas;

    INSERT INTO dw.FactEventosVentas(
        FechaEventoKey, TipoEventoKey,
        ClienteKey, ProductoKey, VendedorKey, DistritoClienteKey, DistritoVendedorKey,
        NumFactura, SecuenciaEvento,
        Cantidad, ImporteNeto, ImporteCosto, Margen
    )
    SELECT
        f.FechaFacturaKey, @TipoVenta,
        f.ClienteKey, f.ProductoKey, f.VendedorKey, f.DistritoClienteKey, f.DistritoVendedorKey,
        f.NumFactura, 1,
        +f.Cantidad, +f.ImporteNeto, +f.ImporteCosto, +f.Margen
    FROM dw.FactVentasLinea f;

    INSERT INTO dw.FactEventosVentas(
        FechaEventoKey, TipoEventoKey,
        ClienteKey, ProductoKey, VendedorKey, DistritoClienteKey, DistritoVendedorKey,
        NumFactura, SecuenciaEvento,
        Cantidad, ImporteNeto, ImporteCosto, Margen
    )
    SELECT
        ISNULL(f.FechaCancelacionKey, f.FechaFacturaKey), @TipoAnul,
        f.ClienteKey, f.ProductoKey, f.VendedorKey, f.DistritoClienteKey, f.DistritoVendedorKey,
        f.NumFactura, 1,
        -f.Cantidad, -f.ImporteNeto, -f.ImporteCosto, -f.Margen
    FROM dw.FactVentasLinea f
    WHERE f.EstadoFactura='3';
END
GO

/*============================================================
  6) ORQUESTADORES
============================================================*/

------------------------------------------------------------
-- 6.1 FULL INIT
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Run_ETL_Ventas_FullInit
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @BatchId BIGINT;
    DECLARE @FechaProceso DATE = CONVERT(date, GETDATE());
    DECLARE @MaxAuditId BIGINT;

    EXEC stg.usp_NewBatch @BatchId=@BatchId OUTPUT;

    BEGIN TRY
        BEGIN TRAN;

        EXEC stg.usp_Extract_Ventas_ToSTG_Full @BatchId=@BatchId;
        EXEC stg.usp_DQ_Validate_Ventas_Full  @BatchId=@BatchId;

        EXEC dw.usp_Load_DimFecha_FromSTG     @BatchId=@BatchId;
        EXEC dw.usp_Load_DimDistrito_SCD1     @BatchId=@BatchId, @FechaProceso=@FechaProceso;
        EXEC dw.usp_SCD2_Load_Dimensions      @BatchId=@BatchId, @FechaProceso=@FechaProceso;

        -- Hecho: en init, tratamos todas las facturas como afectadas
        -- Reusar el upsert: crea una “lista” de afectadas desde el STG
        EXEC dw.usp_Upsert_FactVentasLinea_FromSTG @BatchId=@BatchId;

        EXEC dw.usp_Load_FactEventosVentas_FromBase;

        SELECT @MaxAuditId = ISNULL(MAX(AuditId),0) FROM BDVENTAS.aud.AUD_CAMBIOS;

        UPDATE dw.ETL_Control
           SET UltimoAuditId=@MaxAuditId,
               UltimaEjecucion=SYSDATETIME(),
               Estado='OK',
               Mensaje=CONCAT('FULL INIT OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId)
         WHERE Proceso='VENTAS';

        COMMIT;
        PRINT CONCAT('✅ FULL INIT OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId);

    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT>0 ROLLBACK;
        DECLARE @err NVARCHAR(4000)=ERROR_MESSAGE();

        UPDATE dw.ETL_Control
           SET UltimaEjecucion=SYSDATETIME(),
               Estado='ERROR',
               Mensaje=@err
         WHERE Proceso='VENTAS';

        THROW;
    END CATCH
END
GO

------------------------------------------------------------
-- 6.2 DELTA (incremental formal)
------------------------------------------------------------
CREATE OR ALTER PROCEDURE dw.usp_Run_ETL_Ventas_Delta
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @BatchId BIGINT;
    DECLARE @FechaProceso DATE = CONVERT(date, GETDATE());
    DECLARE @MaxAuditId BIGINT;

    EXEC stg.usp_NewBatch @BatchId=@BatchId OUTPUT;

    BEGIN TRY
        BEGIN TRAN;

        EXEC stg.usp_Extract_Ventas_ToSTG_Delta @BatchId=@BatchId, @MaxAuditId=@MaxAuditId OUTPUT;

        -- Delta vacío => solo avanzar watermark y salir
        IF NOT EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_CLIENTE WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_PRODUCTO WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_VENDEDOR WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_DISTRITO WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_Deletes  WHERE BatchId=@BatchId)
        BEGIN
            UPDATE dw.ETL_Control
               SET UltimoAuditId=@MaxAuditId,
                   UltimaEjecucion=SYSDATETIME(),
                   Estado='OK',
                   Mensaje=CONCAT('DELTA vacío. AuditId=',@MaxAuditId)
             WHERE Proceso='VENTAS';

            COMMIT;
            PRINT CONCAT('✅ DELTA OK (sin cambios). BatchId=',@BatchId,' AuditId=',@MaxAuditId);
            RETURN;
        END

        EXEC stg.usp_DQ_Validate_Ventas_Delta @BatchId=@BatchId;

        EXEC dw.usp_Load_DimFecha_FromSTG @BatchId=@BatchId;
        EXEC dw.usp_Load_DimDistrito_SCD1 @BatchId=@BatchId, @FechaProceso=@FechaProceso;
        EXEC dw.usp_SCD2_Load_Dimensions  @BatchId=@BatchId, @FechaProceso=@FechaProceso;

        EXEC dw.usp_Upsert_FactVentasLinea_FromSTG @BatchId=@BatchId;
        EXEC dw.usp_Load_FactEventosVentas_Incremental @BatchId=@BatchId;

        -- Validación grano
        IF EXISTS (
            SELECT 1 FROM dw.FactVentasLinea
            GROUP BY NumFactura, ProductoKey
            HAVING COUNT(*)>1
        ) THROW 82001,'VALIDACION: duplicados en FactVentasLinea (grano).',1;

        UPDATE dw.ETL_Control
           SET UltimoAuditId=@MaxAuditId,
               UltimaEjecucion=SYSDATETIME(),
               Estado='OK',
               Mensaje=CONCAT('DELTA OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId,' FechaProceso=',CONVERT(varchar(10),@FechaProceso,120))
         WHERE Proceso='VENTAS';

        COMMIT;
        PRINT CONCAT('✅ DELTA OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId);

    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT>0 ROLLBACK;
        DECLARE @err NVARCHAR(4000)=ERROR_MESSAGE();

        UPDATE dw.ETL_Control
           SET UltimaEjecucion=SYSDATETIME(),
               Estado='ERROR',
               Mensaje=@err
         WHERE Proceso='VENTAS';

        THROW;
    END CATCH
END
GO

/*============================================================
  7) EVIDENCIA FORMAL (OLTP: FACTURA/DETALLE en 30 días)
============================================================*/
SELECT Tabla, Operacion, COUNT(*) AS Cnt
FROM BDVENTAS.aud.AUD_CAMBIOS
WHERE ChangeDtm >= DATEADD(DAY,-30, SYSDATETIME())
  AND Tabla IN ('FACTURA','DETALLE_FACTURA')
GROUP BY Tabla, Operacion
ORDER BY Tabla, Operacion;
GO

--Verificando procedmientos creados

SELECT name,create_date, modify_date
FROM sys.objects
WHERE type='P'
ORDER BY name;

--vERIFICANDO

SELECT 
    s.name  AS SchemaName,
    p.name  AS ProcedureName,
    p.create_date,
    p.modify_date
FROM sys.procedures p
JOIN sys.schemas s 
  ON s.schema_id = p.schema_id
WHERE p.name = 'usp_Run_ETL_Ventas_Final';

--Otra comprobacion
SELECT OBJECT_ID('DWVENTAS_FINAL.dw.usp_Run_ETL_Ventas_Final') AS ObjId;
--Otra
SELECT
    s.name  AS SchemaName,
    p.name  AS ProcName
FROM DWVENTAS_FINAL.sys.procedures p
JOIN DWVENTAS_FINAL.sys.schemas s
  ON s.schema_id = p.schema_id
WHERE p.name LIKE '%usp_Run_ETL_Ventas%';




---Solución------------------------------------------
-----------------------------------------------------
USE DWVENTAS_FINAL;
GO

/*============================================================
  WRAPPER FORMAL: usp_Run_ETL_Ventas_Final
  - Si es primera vez (DW vacío / watermark en 0) => FullInit
  - Si ya tiene watermark => Delta
============================================================*/
CREATE OR ALTER PROCEDURE dw.usp_Run_ETL_Ventas_Final
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @UltimoAuditId BIGINT =
        (SELECT UltimoAuditId FROM dw.ETL_Control WHERE Proceso='VENTAS');

    -- Si aún no hay watermark o está en 0 => FullInit
    IF @UltimoAuditId IS NULL OR @UltimoAuditId = 0
    BEGIN
        EXEC dw.usp_Run_ETL_Ventas_FullInit;
        RETURN;
    END

    -- Caso normal => Delta incremental
    EXEC dw.usp_Run_ETL_Ventas_Delta;
END
GO

/*============================================================
  OPCIONAL: wrapper en dbo para invocar fácil desde otras BD
============================================================*/
--CREATE OR ALTER PROCEDURE dbo.usp_Run_ETL_Ventas_Final
--AS
--BEGIN
    --SET NOCOUNT ON;
    --EXEC dw.usp_Run_ETL_Ventas_Final;
--END
--GO


-----Para corrección de SCD02 diario-------------
USE DWVENTAS_FINAL;
GO

/*=====================================================================
  PATCH LIMPIO - FIX SCD2 + GUARDRAILS
  --------------------------------------------------------------------
  PROBLEMA:
    Al correr Delta 2 veces el mismo día, @FechaProceso = CONVERT(date,GETDATE())
    => intenta insertar nueva versión SCD2 con FechaInicio=@FechaProceso
    => choca con UQ_DimProducto_SCD (COD_PRO, FechaInicio)

  SOLUCIÓN:
    Hacer idempotente el insert de la "nueva versión" en SCD2:
      - Insertar versión @FechaProceso SOLO SI NO EXISTE YA para ese día.

  EXTRA:
    Guardrail: validar existencia de BDVENTAS y aud.AUD_CAMBIOS.
=====================================================================*/


/*============================================================
  1) GUARDRAIL: OLTP audit listo
============================================================*/
CREATE OR ALTER PROCEDURE stg.usp_Assert_OLTP_Audit_Ready
AS
BEGIN
    SET NOCOUNT ON;

    IF DB_ID('BDVENTAS') IS NULL
        THROW 50001, 'OLTP no existe: BDVENTAS. Verifica nombre/instancia.', 1;

    IF OBJECT_ID('BDVENTAS.aud.AUD_CAMBIOS','U') IS NULL
        THROW 50002, 'No existe BDVENTAS.aud.AUD_CAMBIOS. Ejecuta el script OLTP AUDIT.', 1;
END
GO


/*============================================================
  2) FIX SCD2: dw.usp_SCD2_Load_Dimensions (IDEMPOTENTE)
============================================================*/
CREATE OR ALTER PROCEDURE dw.usp_SCD2_Load_Dimensions
    @BatchId BIGINT,
    @FechaProceso DATE
AS
BEGIN
    SET NOCOUNT ON;

    /*========================================================
      A) INSERT NUEVOS (baseline 19000101..99991231)
    ========================================================*/
    INSERT INTO dw.DimProducto(COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO,FechaInicio,FechaFin,Activo)
    SELECT s.COD_PRO,s.DES_PRO,s.PRE_PRO,s.UNI_PRO,s.LIN_PRO,s.IMP_PRO,'19000101','99991231',1
    FROM stg.stg_PRODUCTO s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimProducto d WHERE d.COD_PRO=s.COD_PRO);

    INSERT INTO dw.DimCliente(COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,TIP_CLI,CON_CLI,FEC_REG,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_CLI,s.RSO_CLI,s.DIR_CLI,s.TLF_CLI,s.RUC_CLI,s.TIP_CLI,s.CON_CLI,s.FEC_REG,s.COD_DIS,'19000101','99991231',1
    FROM stg.stg_CLIENTE s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimCliente d WHERE d.COD_CLI=s.COD_CLI);

    INSERT INTO dw.DimVendedor(COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_VEN,s.NOM_VEN,s.APE_VEN,s.SUE_VEN,s.FIN_VEN,s.TIP_VEN,s.COD_DIS,'19000101','99991231',1
    FROM stg.stg_VENDEDOR s
    WHERE s.BatchId=@BatchId
      AND NOT EXISTS (SELECT 1 FROM dw.DimVendedor d WHERE d.COD_VEN=s.COD_VEN);


    /*========================================================
      B) CAMBIOS: cerrar activo si cambió (SCD2)
    ========================================================*/
    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimProducto d
    JOIN stg.stg_PRODUCTO s
      ON s.COD_PRO=d.COD_PRO AND s.BatchId=@BatchId
    WHERE d.Activo=1
      AND (d.DES_PRO<>s.DES_PRO OR d.PRE_PRO<>s.PRE_PRO OR d.UNI_PRO<>s.UNI_PRO OR d.LIN_PRO<>s.LIN_PRO OR d.IMP_PRO<>s.IMP_PRO);

    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimCliente d
    JOIN stg.stg_CLIENTE s
      ON s.COD_CLI=d.COD_CLI AND s.BatchId=@BatchId
    WHERE d.Activo=1
      AND (
            d.RSO_CLI<>s.RSO_CLI OR d.DIR_CLI<>s.DIR_CLI OR d.TLF_CLI<>s.TLF_CLI
         OR ISNULL(d.RUC_CLI,'')<>ISNULL(s.RUC_CLI,'')
         OR d.TIP_CLI<>s.TIP_CLI OR d.CON_CLI<>s.CON_CLI
         OR d.FEC_REG<>s.FEC_REG OR d.COD_DIS<>s.COD_DIS
      );

    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimVendedor d
    JOIN stg.stg_VENDEDOR s
      ON s.COD_VEN=d.COD_VEN AND s.BatchId=@BatchId
    WHERE d.Activo=1
      AND (
           d.NOM_VEN<>s.NOM_VEN OR d.APE_VEN<>s.APE_VEN OR d.SUE_VEN<>s.SUE_VEN
        OR d.FIN_VEN<>s.FIN_VEN OR d.TIP_VEN<>s.TIP_VEN OR d.COD_DIS<>s.COD_DIS
      );


    /*========================================================
      C) INSERT NUEVA VERSIÓN (SCD2) - IDEMPOTENTE POR DÍA
      ----------------------------------------------------------------
      Solo inserta si:
        - se cerró versión anterior ayer (FechaFin = @FechaProceso-1)
        - y NO existe ya la versión con FechaInicio=@FechaProceso
    ========================================================*/
    INSERT INTO dw.DimProducto(COD_PRO,DES_PRO,PRE_PRO,UNI_PRO,LIN_PRO,IMP_PRO,FechaInicio,FechaFin,Activo)
    SELECT s.COD_PRO,s.DES_PRO,s.PRE_PRO,s.UNI_PRO,s.LIN_PRO,s.IMP_PRO,@FechaProceso,'99991231',1
    FROM stg.stg_PRODUCTO s
    WHERE s.BatchId=@BatchId
      AND EXISTS (
          SELECT 1
          FROM dw.DimProducto d
          WHERE d.COD_PRO=s.COD_PRO
            AND d.Activo=0
            AND d.FechaFin=DATEADD(DAY,-1,@FechaProceso)
      )
      AND NOT EXISTS (
          SELECT 1
          FROM dw.DimProducto d2
          WHERE d2.COD_PRO=s.COD_PRO
            AND d2.FechaInicio=@FechaProceso
      );

    INSERT INTO dw.DimCliente(COD_CLI,RSO_CLI,DIR_CLI,TLF_CLI,RUC_CLI,TIP_CLI,CON_CLI,FEC_REG,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_CLI,s.RSO_CLI,s.DIR_CLI,s.TLF_CLI,s.RUC_CLI,s.TIP_CLI,s.CON_CLI,s.FEC_REG,s.COD_DIS,@FechaProceso,'99991231',1
    FROM stg.stg_CLIENTE s
    WHERE s.BatchId=@BatchId
      AND EXISTS (
          SELECT 1
          FROM dw.DimCliente d
          WHERE d.COD_CLI=s.COD_CLI
            AND d.Activo=0
            AND d.FechaFin=DATEADD(DAY,-1,@FechaProceso)
      )
      AND NOT EXISTS (
          SELECT 1
          FROM dw.DimCliente d2
          WHERE d2.COD_CLI=s.COD_CLI
            AND d2.FechaInicio=@FechaProceso
      );

    INSERT INTO dw.DimVendedor(COD_VEN,NOM_VEN,APE_VEN,SUE_VEN,FIN_VEN,TIP_VEN,COD_DIS,FechaInicio,FechaFin,Activo)
    SELECT s.COD_VEN,s.NOM_VEN,s.APE_VEN,s.SUE_VEN,s.FIN_VEN,s.TIP_VEN,s.COD_DIS,@FechaProceso,'99991231',1
    FROM stg.stg_VENDEDOR s
    WHERE s.BatchId=@BatchId
      AND EXISTS (
          SELECT 1
          FROM dw.DimVendedor d
          WHERE d.COD_VEN=s.COD_VEN
            AND d.Activo=0
            AND d.FechaFin=DATEADD(DAY,-1,@FechaProceso)
      )
      AND NOT EXISTS (
          SELECT 1
          FROM dw.DimVendedor d2
          WHERE d2.COD_VEN=s.COD_VEN
            AND d2.FechaInicio=@FechaProceso
      );


    /*========================================================
      D) SOFT DELETE (si OLTP borró)
    ========================================================*/
    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimCliente d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId AND x.Tabla='CLIENTE' AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_CLI=', d.COD_CLI)
    WHERE d.Activo=1;

    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimProducto d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId AND x.Tabla='PRODUCTO' AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_PRO=', d.COD_PRO)
    WHERE d.Activo=1;

    UPDATE d
       SET d.FechaFin = DATEADD(DAY,-1,@FechaProceso),
           d.Activo   = 0
    FROM dw.DimVendedor d
    JOIN stg.stg_Deletes x
      ON x.BatchId=@BatchId AND x.Tabla='VENDEDOR' AND x.Operacion='D'
     AND x.BusinessKey = CONCAT('COD_VEN=', d.COD_VEN)
    WHERE d.Activo=1;


    /*========================================================
      E) VALIDACIÓN: 1 activa por BK
    ========================================================*/
    IF EXISTS (SELECT COD_CLI FROM dw.DimCliente WHERE Activo=1 GROUP BY COD_CLI HAVING COUNT(*)>1)
        THROW 71001,'SCD2 ERROR: >1 activa en DimCliente.',1;

    IF EXISTS (SELECT COD_PRO FROM dw.DimProducto WHERE Activo=1 GROUP BY COD_PRO HAVING COUNT(*)>1)
        THROW 71002,'SCD2 ERROR: >1 activa en DimProducto.',1;

    IF EXISTS (SELECT COD_VEN FROM dw.DimVendedor WHERE Activo=1 GROUP BY COD_VEN HAVING COUNT(*)>1)
        THROW 71003,'SCD2 ERROR: >1 activa en DimVendedor.',1;
END
GO


/*============================================================
  3) INYECTAR GUARDRAIL EN ORQUESTADORES (CÓDIGO LIMPIO)
  - Reemplaza SOLO el inicio de cada proc para incluir la validación.
============================================================*/
CREATE OR ALTER PROCEDURE dw.usp_Run_ETL_Ventas_FullInit
AS
BEGIN
    SET NOCOUNT ON;

    -- ✅ Guardrail: OLTP + audit listos
    EXEC stg.usp_Assert_OLTP_Audit_Ready;

    DECLARE @BatchId BIGINT;
    DECLARE @FechaProceso DATE = CONVERT(date, GETDATE());
    DECLARE @MaxAuditId BIGINT;

    EXEC stg.usp_NewBatch @BatchId=@BatchId OUTPUT;

    BEGIN TRY
        BEGIN TRAN;

        EXEC stg.usp_Extract_Ventas_ToSTG_Full @BatchId=@BatchId;
        EXEC stg.usp_DQ_Validate_Ventas_Full  @BatchId=@BatchId;

        EXEC dw.usp_Load_DimFecha_FromSTG     @BatchId=@BatchId;
        EXEC dw.usp_Load_DimDistrito_SCD1     @BatchId=@BatchId, @FechaProceso=@FechaProceso;
        EXEC dw.usp_SCD2_Load_Dimensions      @BatchId=@BatchId, @FechaProceso=@FechaProceso;

        EXEC dw.usp_Upsert_FactVentasLinea_FromSTG @BatchId=@BatchId;
        EXEC dw.usp_Load_FactEventosVentas_FromBase;

        SELECT @MaxAuditId = ISNULL(MAX(AuditId),0) FROM BDVENTAS.aud.AUD_CAMBIOS;

        UPDATE dw.ETL_Control
           SET UltimoAuditId=@MaxAuditId,
               UltimaEjecucion=SYSDATETIME(),
               Estado='OK',
               Mensaje=CONCAT('FULL INIT OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId)
         WHERE Proceso='VENTAS';

        COMMIT;
        PRINT CONCAT('✅ FULL INIT OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId);

    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT>0 ROLLBACK;
        DECLARE @err NVARCHAR(4000)=ERROR_MESSAGE();

        UPDATE dw.ETL_Control
           SET UltimaEjecucion=SYSDATETIME(),
               Estado='ERROR',
               Mensaje=@err
         WHERE Proceso='VENTAS';

        THROW;
    END CATCH
END
GO


CREATE OR ALTER PROCEDURE dw.usp_Run_ETL_Ventas_Delta
AS
BEGIN
    SET NOCOUNT ON;

    -- ✅ Guardrail: OLTP + audit listos
    EXEC stg.usp_Assert_OLTP_Audit_Ready;

    DECLARE @BatchId BIGINT;
    DECLARE @FechaProceso DATE = CONVERT(date, GETDATE());
    DECLARE @MaxAuditId BIGINT;

    EXEC stg.usp_NewBatch @BatchId=@BatchId OUTPUT;

    BEGIN TRY
        BEGIN TRAN;

        EXEC stg.usp_Extract_Ventas_ToSTG_Delta @BatchId=@BatchId, @MaxAuditId=@MaxAuditId OUTPUT;

        IF NOT EXISTS (SELECT 1 FROM stg.stg_FACTURA WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_CLIENTE WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_PRODUCTO WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_VENDEDOR WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_DISTRITO WHERE BatchId=@BatchId)
           AND NOT EXISTS (SELECT 1 FROM stg.stg_Deletes  WHERE BatchId=@BatchId)
        BEGIN
            UPDATE dw.ETL_Control
               SET UltimoAuditId=@MaxAuditId,
                   UltimaEjecucion=SYSDATETIME(),
                   Estado='OK',
                   Mensaje=CONCAT('DELTA vacío. AuditId=',@MaxAuditId)
             WHERE Proceso='VENTAS';

            COMMIT;
            PRINT CONCAT('✅ DELTA OK (sin cambios). BatchId=',@BatchId,' AuditId=',@MaxAuditId);
            RETURN;
        END

        EXEC stg.usp_DQ_Validate_Ventas_Delta @BatchId=@BatchId;

        EXEC dw.usp_Load_DimFecha_FromSTG @BatchId=@BatchId;
        EXEC dw.usp_Load_DimDistrito_SCD1 @BatchId=@BatchId, @FechaProceso=@FechaProceso;
        EXEC dw.usp_SCD2_Load_Dimensions  @BatchId=@BatchId, @FechaProceso=@FechaProceso;

        EXEC dw.usp_Upsert_FactVentasLinea_FromSTG @BatchId=@BatchId;
        EXEC dw.usp_Load_FactEventosVentas_Incremental @BatchId=@BatchId;

        IF EXISTS (
            SELECT 1 FROM dw.FactVentasLinea
            GROUP BY NumFactura, ProductoKey
            HAVING COUNT(*)>1
        ) THROW 82001,'VALIDACION: duplicados en FactVentasLinea (grano).',1;

        UPDATE dw.ETL_Control
           SET UltimoAuditId=@MaxAuditId,
               UltimaEjecucion=SYSDATETIME(),
               Estado='OK',
               Mensaje=CONCAT('DELTA OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId,' FechaProceso=',CONVERT(varchar(10),@FechaProceso,120))
         WHERE Proceso='VENTAS';

        COMMIT;
        PRINT CONCAT('✅ DELTA OK. BatchId=',@BatchId,' AuditId=',@MaxAuditId);

    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT>0 ROLLBACK;
        DECLARE @err NVARCHAR(4000)=ERROR_MESSAGE();

        UPDATE dw.ETL_Control
           SET UltimaEjecucion=SYSDATETIME(),
               Estado='ERROR',
               Mensaje=@err
         WHERE Proceso='VENTAS';

        THROW;
    END CATCH
END
GO
