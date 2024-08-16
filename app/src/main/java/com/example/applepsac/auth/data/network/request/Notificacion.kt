package com.example.applepsac.auth.data.network.request

data class Notificacion(
    val id: Int,
    val descripcion: String,
    val estado: String,
    val visto: Int,
    val t_usuario_id: Int
)