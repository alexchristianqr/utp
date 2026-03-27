

------------------------------------------------------------
---para  la creción de consultas ---------------------------
------------------------------------------------------------
USE DWVENTAS_FINAL;
GO

/*============================================================
  ETL INCREMENTAL (DELTA)
  - Lee BDVENTAS.aud.AUD_CAMBIOS con watermark
  - Carga dims (SCD2) y hechos (UPSERT por facturas afectadas)
============================================================*/

EXEC dw.usp_Run_ETL_Ventas_Delta;
GO

-- Evidencia: watermark movió
SELECT * FROM dw.ETL_Control WHERE Proceso='VENTAS';
GO

------------------------------------------------------------------
------EJERCICIOS DE CONSULTAS-------------------------------------
------------------------------------------------------------------
USE DWVENTAS_FINAL;
GO

DECLARE @COD_PRO CHAR(5) = 'P015';
DECLARE @FA1 VARCHAR(12) = 'FA021';
DECLARE @FA2 VARCHAR(12) = 'FA022';

;WITH Base AS (
    SELECT
        f.NumFactura,
        dp.COD_PRO,
        dp.DES_PRO,
        df.Fecha AS FechaFactura,

        -- Precio venta y cantidades (del hecho)
        f.Cantidad,
        f.PrecioVentaUnit,
        f.ImporteNeto,

        -- Costo histórico (SCD2) tomado del DimProducto (vigente en la fecha de factura)
        f.CostoUnitarioBase,
        f.ImporteCosto,
        f.Margen
    FROM dw.FactVentasLinea f
    JOIN dw.DimProducto dp
      ON dp.ProductoKey = f.ProductoKey
    JOIN dw.DimFecha df
      ON df.FechaKey = f.FechaFacturaKey
    WHERE dp.COD_PRO = @COD_PRO
      AND f.NumFactura IN (@FA1, @FA2)
),
Pivoted AS (
    SELECT
        COD_PRO,
        MAX(DES_PRO) AS DES_PRO,

        MAX(CASE WHEN NumFactura=@FA1 THEN FechaFactura END) AS FechaFA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN FechaFactura END) AS FechaFA2,

        MAX(CASE WHEN NumFactura=@FA1 THEN Cantidad END) AS CantFA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN Cantidad END) AS CantFA2,

        MAX(CASE WHEN NumFactura=@FA1 THEN PrecioVentaUnit END) AS PVU_FA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN PrecioVentaUnit END) AS PVU_FA2,

        MAX(CASE WHEN NumFactura=@FA1 THEN CostoUnitarioBase END) AS CostoBase_FA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN CostoUnitarioBase END) AS CostoBase_FA2,

        MAX(CASE WHEN NumFactura=@FA1 THEN ImporteNeto END) AS VentaNeta_FA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN ImporteNeto END) AS VentaNeta_FA2,

        MAX(CASE WHEN NumFactura=@FA1 THEN ImporteCosto END) AS CostoTotal_FA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN ImporteCosto END) AS CostoTotal_FA2,

        MAX(CASE WHEN NumFactura=@FA1 THEN Margen END) AS Margen_FA1,
        MAX(CASE WHEN NumFactura=@FA2 THEN Margen END) AS Margen_FA2
    FROM Base
    GROUP BY COD_PRO
)
SELECT
    'DETALLE_FA021' AS TipoFila,
    COD_PRO, DES_PRO,
    FechaFA1 AS FechaFactura,
    CantFA1  AS Cantidad,
    PVU_FA1  AS PrecioVentaUnit,
    CostoBase_FA1 AS CostoUnitarioHistorico,
    VentaNeta_FA1 AS ImporteNeto,
    CostoTotal_FA1 AS ImporteCosto,
    Margen_FA1 AS Margen,
    CAST(NULL AS MONEY) AS Delta_Margen,
    CAST(NULL AS MONEY) AS Delta_CostoUnit,
    CAST(NULL AS MONEY) AS Delta_VentaNeta
FROM Pivoted

UNION ALL

SELECT
    'DETALLE_FA022' AS TipoFila,
    COD_PRO, DES_PRO,
    FechaFA2,
    CantFA2,
    PVU_FA2,
    CostoBase_FA2,
    VentaNeta_FA2,
    CostoTotal_FA2,
    Margen_FA2,
    CAST(NULL AS MONEY),
    CAST(NULL AS MONEY),
    CAST(NULL AS MONEY)
FROM Pivoted

UNION ALL

SELECT
    'COMPARACION_FA022_vs_FA021' AS TipoFila,
    COD_PRO, DES_PRO,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    CAST(Margen_FA2 - Margen_FA1 AS MONEY)          AS Delta_Margen,
    CAST(CostoBase_FA2 - CostoBase_FA1 AS MONEY)    AS Delta_CostoUnit,
    CAST(VentaNeta_FA2 - VentaNeta_FA1 AS MONEY)    AS Delta_VentaNeta
FROM Pivoted;
GO


----Verificacion
USE DWVENTAS_FINAL;
GO
SELECT COD_PRO, PRE_PRO, FechaInicio, FechaFin, Activo
FROM dw.DimProducto
WHERE COD_PRO='P015'
ORDER BY FechaInicio;
GO



