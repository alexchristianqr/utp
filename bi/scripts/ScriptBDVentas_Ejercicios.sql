---------------------------------------------------------
---------------------------------------------------------
--------- INTERACCIONES ---------------------------------
----------------------------------------------------------
--1. Generar una nueva transacción (venta)
--Verifica:
USE BDVENTAS
SELECT * FROM FACTURA
SELECT * FROM DETALLE_FACTURA
SELECT * FROM PRODUCTO
-------------------------------------

-- Debes tener carga inicial (solo una vez)
EXEC DWVENTAS_FINAL.dw.usp_Run_ETL_Ventas_FullInit;
GO

USE BDVENTAS;
GO
SET DATEFORMAT DMY;
GO

/*============================================================
  EJERCICIO 1
  - Producto: P015
  - Venta 1: FA021 (con costo/valor "viejo" en DimProducto)
  - Cambio: PRODUCTO.PRE_PRO (una sola vez)
  - Venta 2: FA022 (con costo/valor "nuevo" en DimProducto)
============================================================*/

DECLARE @COD_PRO CHAR(5) = 'P015';
DECLARE @FA1 VARCHAR(12) = 'FA021';
DECLARE @FA2 VARCHAR(12) = 'FA022';

-- (0) Limpieza defensiva si ya corriste el ejercicio antes
DELETE FROM dbo.DETALLE_FACTURA WHERE NUM_FAC IN (@FA1,@FA2);
DELETE FROM dbo.FACTURA        WHERE NUM_FAC IN (@FA1,@FA2);
GO

/*------------------------------------------------------------
  1) Venta 1 (FA021) - usa precio de venta PRE_VEN=15 (ejemplo)
------------------------------------------------------------*/
DECLARE @COD_PRO CHAR(5) = 'P015';
DECLARE @FA1 VARCHAR(12) = 'FA021';
DECLARE @FA2 VARCHAR(12) = 'FA022';

INSERT INTO dbo.FACTURA(NUM_FAC,FEC_FAC,COD_CLI,FEC_CAN,EST_FAC,COD_VEN,POR_IGV)
VALUES(@FA1,'01-07-2015','C001','05-08-2015','2','V01',0.18);

INSERT INTO dbo.DETALLE_FACTURA(NUM_FAC,COD_PRO,CAN_VEN,PRE_VEN)
VALUES(@FA1,@COD_PRO,20,8);



/*------------------------------------------------------------
  2) Cambiamos precio base del producto (PRODUCTO.PRE_PRO)
     (Esto generará un registro U en aud.AUD_CAMBIOS)
------------------------------------------------------------*/
DECLARE @COD_PRO CHAR(5) = 'P015'
SELECT COD_PRO, DES_PRO, PRE_PRO AS PrecioAntes
FROM dbo.PRODUCTO
WHERE COD_PRO=@COD_PRO;

DECLARE @FA2 VARCHAR(12) = 'FA022';
DECLARE @COD_PRO CHAR(5) = 'P015'
UPDATE dbo.PRODUCTO
SET PRE_PRO = 10
WHERE  COD_PRO=@COD_PRO;
--WHERE NUM_FAC=@FA2 AND COD_PRO=@COD_PRO;

DECLARE @COD_PRO CHAR(5) = 'P015'
SELECT COD_PRO, DES_PRO, PRE_PRO AS PrecioDespues
FROM dbo.PRODUCTO
WHERE COD_PRO=@COD_PRO;

/*------------------------------------------------------------
  3) Venta 2 (FA022) - precio de venta PRE_VEN=15 (igual)
     (La diferencia estará en el costo base histórico del DW)
------------------------------------------------------------*/

DECLARE @COD_PRO CHAR(5) = 'P015';
DECLARE @FA1 VARCHAR(12) = 'FA021';
DECLARE @FA2 VARCHAR(12) = 'FA022';

INSERT INTO dbo.FACTURA(NUM_FAC,FEC_FAC,COD_CLI,FEC_CAN,EST_FAC,COD_VEN,POR_IGV)
VALUES(@FA2,'10-08-2015','C001','12-08-2015','2','V01',0.18);

INSERT INTO dbo.DETALLE_FACTURA(NUM_FAC,COD_PRO,CAN_VEN,PRE_VEN)
VALUES(@FA2,@COD_PRO,30,10);

GO

/*------------------------------------------------------------
  Evidencia OLTP: cambios generados en el journal
------------------------------------------------------------*/
SELECT TOP 50 AuditId, Tabla, Operacion, BusinessKey, ChangeDtm
FROM aud.AUD_CAMBIOS
WHERE Tabla IN ('FACTURA','DETALLE_FACTURA','PRODUCTO')
ORDER BY AuditId DESC;
GO


SELECT * FROM PRODUCTO
SELECT * FROM FACTURA
SELECT * FROM DETALLE_FACTURA AS D
INNER JOIN PRODUCTO AS P ON (P.COD_PRO=D.COD_PRO)
WHERE D.COD_PRO='P015'
