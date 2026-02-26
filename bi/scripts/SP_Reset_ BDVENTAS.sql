USE BDVENTAS
GO

IF OBJECT_ID('dbo.usp_ResetBDVENTAS') IS NOT NULL
    DROP PROCEDURE dbo.usp_ResetBDVENTAS
GO

CREATE PROCEDURE dbo.usp_ResetBDVENTAS
AS
BEGIN
    SET NOCOUNT ON;
    SET DATEFORMAT DMY;

    -----------------------------------------------------
    -- TABLAS EXCLUIDAS (NO SE TOCAN)
    -----------------------------------------------------
    DECLARE @TablasExcluidas TABLE
    (
        SchemaName SYSNAME,
        TableName SYSNAME
    )

    INSERT INTO @TablasExcluidas VALUES
        ('aud','AUD_CAMBIOS')
        -- Agrega más si quieres:
        -- ,('dbo','OTRA_TABLA')

    -----------------------------------------------------
    -- 1️⃣ CREAR TABLAS SI NO EXISTEN (si no están excluidas)
    -----------------------------------------------------

    -- DISTRITO
    IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='DISTRITO')
       AND NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='DISTRITO')
    CREATE TABLE dbo.DISTRITO(
        COD_DIS CHAR(5) NOT NULL PRIMARY KEY,
        NOM_DIS VARCHAR(50) NOT NULL
    )

    -- PRODUCTO
    IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='PRODUCTO')
       AND NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='PRODUCTO')
    CREATE TABLE dbo.PRODUCTO(
        COD_PRO CHAR(5) NOT NULL PRIMARY KEY,
        DES_PRO VARCHAR(50) NOT NULL,
        PRE_PRO MONEY NOT NULL,
        SAC_PRO INT NOT NULL,
        SMI_PRO INT NOT NULL,
        UNI_PRO VARCHAR(30) NOT NULL,
        LIN_PRO VARCHAR(30) NOT NULL,
        IMP_PRO VARCHAR(10) NOT NULL
    )

    -- (Repite mismo patrón para las demás tablas…)

    -----------------------------------------------------
    -- 2️⃣ RESET REAL (SI EXISTEN Y NO ESTÁN EXCLUIDAS)
    -----------------------------------------------------

    -- Deshabilitar FK
    EXEC sp_MSforeachtable 'ALTER TABLE ? NOCHECK CONSTRAINT ALL'

    -----------------------------------------------------
    -- BORRADO EN ORDEN (HIJAS → PADRES)
    -----------------------------------------------------

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='DETALLE_FACTURA')
        DELETE FROM dbo.DETALLE_FACTURA

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='DETALLE_COMPRA')
        DELETE FROM dbo.DETALLE_COMPRA

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='ABASTECIMIENTO')
        DELETE FROM dbo.ABASTECIMIENTO

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='FACTURA')
        DELETE FROM dbo.FACTURA

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='ORDEN_COMPRA')
        DELETE FROM dbo.ORDEN_COMPRA

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='CLIENTE')
        DELETE FROM dbo.CLIENTE

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='VENDEDOR')
        DELETE FROM dbo.VENDEDOR

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='PROVEEDOR')
        DELETE FROM dbo.PROVEEDOR

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='PRODUCTO')
        DELETE FROM dbo.PRODUCTO

    IF NOT EXISTS (SELECT 1 FROM @TablasExcluidas WHERE TableName='DISTRITO')
        DELETE FROM dbo.DISTRITO

    -- Rehabilitar FK
    EXEC sp_MSforeachtable 'ALTER TABLE ? WITH CHECK CHECK CONSTRAINT ALL'

END
GO