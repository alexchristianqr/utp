package com.app.apkproductos.reportes
import com.google.gson.annotations.SerializedName

data class Movimiento(
    val id: Int,
    @SerializedName("producto_id") val productoId: Int,
    val tipo: String,           // "ENTRADA" o "SALIDA"
    val cantidad: Int,
    val fecha: String,
    val descripcion: String
)

// Información básica del producto
data class ProductoSimple(
    val id: Int,
    val nombre: String
)
