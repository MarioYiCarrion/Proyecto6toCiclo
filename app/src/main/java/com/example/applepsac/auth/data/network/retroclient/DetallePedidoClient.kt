package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetallePedidoClient {
    @GET("estadosposiciones/posicion/{id}")
    suspend fun getDetallePedido(@Path("id") pedidoId: String): Response<List<DetallePedidoResponse>>
}
