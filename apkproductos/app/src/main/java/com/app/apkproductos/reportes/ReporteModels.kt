package com.app.apkproductos.reportes
import com.google.gson.annotations.SerializedName
// Clase que representa un movimiento individual
import com.app.apkproductos.reportes.ProductoSimple

data class Movimiento(
    val id: Int,
    @SerializedName("producto_id") val productoId: Int,
    val tipo: String,           // "ENTRADA" o "SALIDA"
    val cantidad: Int,
    val fecha: String,
    val descripcion: String
)

// Clase que representa la respuesta de la API para un producto
data class MovimientosResponse(
    val producto: ProductoSimple,
    val movimientos: List<Movimiento>,
    val entradas: List<Movimiento>,
    val salidas: List<Movimiento>
)



// Información básica del producto
data class ProductoSimple(
    val id: Int,
    val nombre: String
)
