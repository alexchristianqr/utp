package com.app.androidutp.productos

import com.app.androidutp.universidad.entidad.CarreraResponse
import com.app.androidutp.universidad.entidad.Estudiante
import com.app.androidutp.universidad.entidad.EstudianteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ProductoService {
    @GET("/productos")
    suspend fun cargarProductos(): Response<ProductoResponse>
}
