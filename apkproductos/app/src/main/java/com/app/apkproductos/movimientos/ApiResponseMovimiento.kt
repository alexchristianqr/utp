package com.app.apkproductos.movimientos

data class ApiResponseMovimiento(
    val message: String,
    val data: List<MovimientoData>
)

data class MovimientoData(
    val id: Int,
    val producto_id: Int,
    val producto_nombre: String?, // agregar si backend lo devuelve
    val tipo: String,
    val cantidad: Int,
    val descripcion: String?
)
