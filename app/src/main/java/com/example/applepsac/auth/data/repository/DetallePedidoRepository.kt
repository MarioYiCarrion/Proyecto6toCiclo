package com.example.applepsac.auth.data.repository

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import com.example.applepsac.auth.data.network.retroclient.DetallePedidoClient

import javax.inject.Inject

class DetallePedidoRepository @Inject constructor(private val api: DetallePedidoClient) {
    suspend fun getDetallePedido(pedidoId: String): List<DetallePedidoResponse> {
        val response = api.getDetallePedido(pedidoId)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching details")
        }
    }
}