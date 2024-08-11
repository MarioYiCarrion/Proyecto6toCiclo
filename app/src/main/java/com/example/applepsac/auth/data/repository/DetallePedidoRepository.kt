package com.example.applepsac.auth.data.repository

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import com.example.applepsac.auth.data.network.service.DetallePedidoService
import javax.inject.Inject

class DetallePedidoRepository @Inject constructor(private val api: DetallePedidoService){
    suspend fun getDetallePedido(): List<DetallePedidoResponse>{
        return api.getDetallePedido()
    }
}