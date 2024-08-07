package com.example.applepsac.auth.data.network.response

data class ActualizacionResponse(
    val results: List<Actualizacion>
)

data class Actualizacion(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
    val estado: String,
    val visto: Int,
    val t_usuario_id: Int
)
