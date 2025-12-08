package com.app.apkproductos.movimientos

import com.app.apkproductos.productos.Producto
import retrofit2.Response
import retrofit2.http.*


data class MovimientoResponse(
    val message: String,
    val data: Any
)

interface MovimientoService {


    @POST("movimientos")
    suspend fun registrarMovimiento(
        @Body body: MovimientoRequest
    ): Response<MovimientoResponse>



    @GET("movimientos/producto/{producto_id}")
    suspend fun obtenerMovimientosPorProducto(
        @Path("producto_id") productoId: Int
    ): Response<ApiResponseMovimiento>





}

