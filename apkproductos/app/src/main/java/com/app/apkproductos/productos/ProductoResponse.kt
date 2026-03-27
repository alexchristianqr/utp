package com.app.apkproductos.productos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


// Asi lo devuelve mi API
@Serializable
data class ProductoResponse (
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: List<Producto>,
    @SerializedName("data2") var data2: Producto? //
)