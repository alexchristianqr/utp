package com.app.apkproductos.movimientos

data class ProductoMovimientoResponse(
    val message: String,
    val data: ProductoMovimientoData?
)

data class ProductoMovimientoData(
    val id: Int,
    val nombre: String,
    val stock: Int
)
