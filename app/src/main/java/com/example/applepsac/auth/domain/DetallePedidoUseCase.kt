package com.example.applepsac.auth.domain

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import com.example.applepsac.auth.data.repository.DetallePedidoRepository
import javax.inject.Inject

class DetallePedidoUseCase @Inject constructor(
    private val detallePedidoRepository: DetallePedidoRepository
) {
    suspend operator fun invoke(pedidoId: String): List<DetallePedidoResponse> {
        return detallePedidoRepository.getDetallePedido(pedidoId)
    }
}
