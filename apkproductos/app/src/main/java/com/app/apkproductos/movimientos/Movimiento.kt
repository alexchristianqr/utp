package com.app.apkproductos.movimientos
import com.google.gson.annotations.SerializedName
/*
data class Movimiento(
    val producto_id: Int,
    val tipo: String,
    val cantidad: Int,
    val descripcion: String?
)*/


data class MovimientoRequest(
    @SerializedName("producto_id") val producto_id: Int,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("descripcion") val descripcion: String? = null
)

