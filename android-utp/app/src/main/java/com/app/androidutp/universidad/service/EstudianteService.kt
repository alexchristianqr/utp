package com.app.androidutp.universidad.service

import com.app.androidutp.universidad.entidad.CarreraResponse
import com.app.androidutp.universidad.entidad.Estudiante
import com.app.androidutp.universidad.entidad.EstudianteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST


interface EstudianteService {
    @GET("/alumnos")
    suspend fun cargarEstudiantes(): Response<EstudianteResponse>

    @POST("/alumno/registrar")
    suspend fun registrarEstudiante(): Response<EstudianteResponse>

    @GET("/carreras")
    suspend fun cargarCarreras(): Response<CarreraResponse>
}
