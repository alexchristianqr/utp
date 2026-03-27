package com.app.apkproductos.productos
import kotlinx.serialization.Serializable

@Serializable
data class RegistrarProductoRequest(
    val nombre: String,
    val descripcion: String,
    val precio: Float,
    val stock: Int,
    val categoria: String,
    val imagen: String? = null   // 🔥 IMPORTANTE
)

@Serializable
data class RegistrarProductoResponse(
    val message: String,
    val data: ProductoRegistrado
)

@Serializable
data class ProductoRegistrado(
    val producto_id: Int,
    val nombre: String,
    val descripcion: String,
    val imagen: String?,
    val precio: Float,
    val stock: Int,
    val categoria: String
)