package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.request.RegistrarRequest
import com.example.applepsac.auth.data.network.response.resgistrarResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IRegistrarUsuario {
    @POST("usuarios")
    suspend fun registrarUser(@Body request: RegistrarRequest): resgistrarResponse
}