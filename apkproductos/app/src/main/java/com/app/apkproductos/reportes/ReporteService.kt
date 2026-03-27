package com.app.apkproductos.reportes

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReporteService {
    @GET("movimientos/{producto_id}")
    suspend fun getMovimientos(
        @Path("producto_id") productoId: Int
    ): Response<ApiResponseMovimiento>

}

// Movimiento individual (tal como lo envía tu backend)
data class MovimientoData(
    val id: Int,
    val producto_id: Int,
    val tipo: String,
    val cantidad: Int,
    val fecha: String,
    val descripcion: String?
)

// Data que viene dentro de "data" en la respuesta
data class MovimientosData(
    val producto: ProductoSimple,
    val movimientos: List<MovimientoData>,
    val entradas: List<MovimientoData>,
    val salidas: List<MovimientoData>
)

// Respuesta completa de la API
data class ApiResponseMovimiento(
    val message: String,
    val data: MovimientosData?
)
