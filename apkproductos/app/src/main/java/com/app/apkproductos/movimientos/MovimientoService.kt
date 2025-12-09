package com.app.apkproductos.movimientos

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class MovimientoResponse(
    val message: String,
    val data: Any
)

interface MovimientoService {
    
    @POST("movimientos")
    suspend fun registrarMovimiento(
        @Body body: MovimientoRequest
    ): Response<MovimientoResponse>
}

