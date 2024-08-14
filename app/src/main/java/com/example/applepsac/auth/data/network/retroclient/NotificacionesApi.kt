package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.request.Notificacion
import com.example.applepsac.auth.data.network.response.NotificacionesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NotificacionesApi {
    @GET("notificaciones/usuario/{id}")
    suspend fun getNotificaciones(@Path("id") userId: Int): NotificacionesResponse
}