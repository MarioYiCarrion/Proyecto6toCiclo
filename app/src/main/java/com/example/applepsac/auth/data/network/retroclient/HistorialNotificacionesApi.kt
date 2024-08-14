package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.response.HistorialNotificacionesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HistorialNotificacionesApi {

    @GET("notificaciones/usuario/historial/{id}")
    suspend fun getHistorialNotificaciones(@Path("id") userId: Int): HistorialNotificacionesResponse
}