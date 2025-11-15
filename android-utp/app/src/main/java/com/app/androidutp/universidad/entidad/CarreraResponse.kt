package com.app.androidutp.universidad.entidad

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CarreraResponse (
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: List<Carrera>
)