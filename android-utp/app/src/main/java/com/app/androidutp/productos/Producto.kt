package com.app.androidutp.productos

import kotlinx.serialization.Serializable

@Serializable
data class Producto(
    var id: Int = 0,
    var nombre: String,
    var descripcion: String,
    var precio: Float,
    var stock: Int,
    var categoria: String,
)