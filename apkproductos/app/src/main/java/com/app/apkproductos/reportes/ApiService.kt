package com.app.apkproductos.reportes

import com.app.apkproductos.reportes.MovimientosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movimientos/{producto_id}")
    suspend fun getMovimientos(
        @Path("producto_id") productoId: Int
    ): Response<MovimientosResponse>

}


/*
data class MovimientosResponseWrapper(
    val data: MovimientosResponse
)*/
