package com.example.applepsac.auth.data.network.request

data class Comentario(
    val descripcion: String,
    val fecha: String,
    val estado: String = "A",
    val activo: Int = 1,
    val califica: Float,
    val t_usuario_id: Int = 1 // usuario prederteminado para que sea anonimo
)
