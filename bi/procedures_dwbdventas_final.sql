exec DWVENTAS_FINAL.dw.usp_Load_FactEventosVentas_FromBase;

exec DWVENTAS_FINAL.dw.usp_Reset_DWVENTAS_FINAL;


EXEC DWVENTAS_FINAL.dw.usp_Run_ETL_Ventas_FullInit;
exec DWVENTAS_FINAL.dw.usp_Run_ETL_Ventas_Final;
EXEC DWVENTAS_FINAL.dw.usp_Run_ETL_Ventas_Delta;
