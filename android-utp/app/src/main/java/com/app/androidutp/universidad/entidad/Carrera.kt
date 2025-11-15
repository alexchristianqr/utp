package com.app.androidutp.universidad.entidad

import kotlinx.serialization.Serializable

@Serializable
data class Carrera (
    var car_id: Int = 0,
    var car_nombre: String
)