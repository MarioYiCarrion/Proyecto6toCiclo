package com.example.applepsac.auth.data.network.response

data class resgistrarResponse(
    val results: List<usuarios>
)

data class usuarios(
    val id: Int,
    val nombre: String,
    val correo: String,
    val estado: String,
    val password: String,
    val activo: Int,
    val nomusuario: String,
    val dni: String,
    val ap_paterno: String,
    val ap_materno: String,
    val direccion: String,
    val celular: String
)
