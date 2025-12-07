package com.app.apkproductos.productos

import com.app.apkproductos.movimientos.ProductoMovimientoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductoService {
    @GET("/productos")
    suspend fun cargarProductos(): Response<ProductoResponse>


    //Se agrego esto mi king prueba v1
    @GET("productos/codigo/{codigo}")
    suspend fun buscarPorCodigo(
        @Path("codigo") codigo: String
    ): Response<ProductoResponse>

    //esto tambien es de prueba v2
    @GET("productos/{id}")
    suspend fun obtenerProductoPorId(
        @Path("id") id: Int
    ): Response<ProductoMovimientoResponse>


}
