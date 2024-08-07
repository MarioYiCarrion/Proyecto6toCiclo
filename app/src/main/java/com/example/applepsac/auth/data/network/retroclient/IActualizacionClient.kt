package com.example.applepsac.auth.data.network.retroclient


import com.example.applepsac.auth.data.network.response.ActualizacionResponse
import retrofit2.http.GET


interface IActualizacionClient {
    @GET("actualizaciones/usuario/1")
    suspend fun getActualizaciones(): ActualizacionResponse
}