package com.example.applepsac.auth.data.network.request

data class RegistrarRequest(
    val dni: String,
    val nombre: String,
    val ap_paterno: String,
    val ap_materno: String,
    val direccion: String,
    val celular: String,
    val correo: String,
    val estado: String,
    val activo: Int,
    val password: String
)
