package com.app.androidutp.universidad.entidad

import kotlinx.serialization.Serializable

@Serializable
data class Estudiante(
    var alu_id: Int = 0,
    var alu_codigo: String,
    var alu_nombres: String,
    var alu_apellidos: String,
    var alu_edad: Int,
    var alu_genero: String,
    var car_id: Int,

)