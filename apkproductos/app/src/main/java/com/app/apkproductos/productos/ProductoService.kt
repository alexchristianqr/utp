package com.app.apkproductos.productos

import com.app.apkproductos.movimientos.ProductoMovimientoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.PartMap


interface ProductoService {
    @GET("/productos")
    suspend fun cargarProductos(): Response<ProductoResponse>

    //SE AGREGO PARA REGISTRA PRODUCTOS
    @POST("/productos")
    suspend fun registrarProducto(
        @Body producto: RegistrarProductoRequest
    ): Response<RegistrarProductoResponse>

    //SE AGREGO PARA MODIFICAR

    @Multipart
    @PUT("/productos/{id}")
    suspend fun actualizarProducto(
        @Path("id") id: Int,
        @PartMap campos: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imagen: MultipartBody.Part?
    ): Response<ProductoResponse>

    /*
    @Multipart
    @PUT("productos/{id}")
    suspend fun actualizarProducto(
        @Path("id") id: Int,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imagen: MultipartBody.Part?
    ): Response<ApiResponse<Producto>>*/



    @Multipart
    @POST("/productos")
    suspend fun registrarProductoMultipart(
        @Part("nombre") nombre: RequestBody,
        @Part("descripcion") descripcion: RequestBody,
        @Part("precio") precio: RequestBody,
        @Part("stock") stock: RequestBody,
        @Part("categoria") categoria: RequestBody,
        @Part imagen: MultipartBody.Part?
    ): Response<RegistrarProductoResponse>




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
