// Nuevo archivo: ActualizarProductoResponse.kt (o ProductoIndividualResponse.kt)

package com.app.apkproductos.productos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ActualizarProductoResponse (
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: List<Producto>, // <--- LISTA
    //@SerializedName("data") var data: Producto?     // <--- OBJETO
)
