package com.app.apkproductos.productos

import retrofit2.Response
import retrofit2.http.GET


interface ProductoService {
    @GET("/productos")
    suspend fun cargarProductos(): Response<ProductoResponse>
}
