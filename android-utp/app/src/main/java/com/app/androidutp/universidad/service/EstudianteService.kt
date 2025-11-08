package com.app.androidutp.universidad.service

import com.app.androidutp.universidad.entidad.Estudiante
import com.app.androidutp.universidad.entidad.EstudianteResponse
import retrofit2.Response
import retrofit2.http.GET


interface EstudianteService {
    @GET("/alumnos")
    suspend fun cargarEstudiantes(): Response<EstudianteResponse>
}
