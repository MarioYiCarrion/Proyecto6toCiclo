package com.example.applepsac.auth.data.repository

import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.data.network.service.SeguimientoPedidoService
import javax.inject.Inject

class SeguimientoPedidoRepository @Inject constructor(private val api: SeguimientoPedidoService) {
    suspend fun getSeguimientoPedido():List<SeguimientoPedidoResponse>{
        return api.getSeguimientoPedido()
    }
}