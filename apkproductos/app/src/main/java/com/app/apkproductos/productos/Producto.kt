package com.app.apkproductos.productos

//import kotlinx.serialization.Serializable
import java.io.Serializable




//version1
data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Float,
    val stock: Int,
    //val cantidad: Int, // se agrego
    val categoria: String,
    val imagen: String? // URL devuelta por la API
) : Serializable







data class Productomodificar(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Float,
    val stock: Int,
    //val cantidad: Int,
    val categoria: String,
    val imagen: String?
) : Serializable



data class Productomodificado(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val stock: Int,
    val precio: Double, // <-- Double
    val categoria: String,
    val imagen: String?
) : java.io.Serializable



