package com.app.apkproductos.productos

import com.google.gson.annotations.SerializedName

// Para respuestas genéricas como DELETE
data class GenericResponse(
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("message") val message: String
)
