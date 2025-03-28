package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.response.NotificacionesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotificacionesApi {
    @GET("notificaciones/usuario/{id}")
    suspend fun getNotificaciones(@Path("id") userId: Int): NotificacionesResponse

    @PUT("notificaciones/visto/{id}")
    suspend fun marcarComoVisto(@Path("id") idNotificacion: Int, @Body body: Map<String, Int>): Response<Unit>
}
