package com.example.applepsac.auth.data.network.service

import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.data.network.retroclient.SeguimientoPedidoClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SeguimientoPedidoService @Inject constructor(private val seguimientoPedidoClient: SeguimientoPedidoClient) {
    suspend fun getSeguimientoPedido(): List<SeguimientoPedidoResponse> {
        return withContext(Dispatchers.IO) {
            val response = seguimientoPedidoClient.getSeguimientoPedido()
            if (response.isSuccessful) {
                response.body() ?: throw IllegalStateException("Response body is null")
            } else {
                throw IllegalStateException("Respuesta no exitosa: ${response.code()}")
            }
        }
    }
}
