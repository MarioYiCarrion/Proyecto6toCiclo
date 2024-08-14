package com.example.applepsac.auth.data.network.service

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetallePedidoService {

    @GET("estadosposiciones/posicion/{pedidoId}")
    suspend fun getDetallePedido(
        @Path("pedidoId") pedidoId: String
    ): List<DetallePedidoResponse> // Cambiado a lista si esperas múltiples detalles

}
