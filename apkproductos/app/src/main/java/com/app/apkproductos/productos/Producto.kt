package com.app.apkproductos.productos

import kotlinx.serialization.Serializable

@Serializable
data class Producto(
    var id: Int = 0,
    var nombre: String,
    var descripcion: String,
    var imagen: String,
    var precio: Float,
    var stock: Int,
    var categoria: String,
)


