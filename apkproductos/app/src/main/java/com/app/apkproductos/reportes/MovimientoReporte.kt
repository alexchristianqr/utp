package com.app.apkproductos.reportes

data class MovimientoReporte(
    val id: Int,
    val producto_id: Int,
    val nombreProd: String,
    val tipo: String,       // "ENTRADA" o "SALIDA"
    val cantidad: Int,
    val fecha: String,
    val descripcion: String
)
