package com.app.androidutp.universidad.entidad

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EstudianteResponse (
//    @SerializedName("listaEstudiantes") var listaEstudiantes: List<Estudiante>
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: List<Estudiante>
)