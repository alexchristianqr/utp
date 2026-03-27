package com.app.apkproductos.reportes
import com.google.gson.annotations.SerializedName


data class MovimientoReporte(
    val id: Int,
    val producto_id: Int,
    val nombreProd: String,
    val tipo: String,
    val cantidad: Int,
    val fecha: String,
    val descripcion: String?
)

