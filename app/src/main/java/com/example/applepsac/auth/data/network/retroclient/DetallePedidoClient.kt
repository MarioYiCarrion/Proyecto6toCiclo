package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import retrofit2.Response
import retrofit2.http.GET

interface DetallePedidoClient {

    @GET("estadosposiciones/posicion/1")
    suspend fun getDetallePedido(): Response<List<DetallePedidoResponse>>

}