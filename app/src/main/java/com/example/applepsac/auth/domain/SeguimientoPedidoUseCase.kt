package com.example.applepsac.auth.domain

import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.data.repository.SeguimientoPedidoRepository
import javax.inject.Inject

class SeguimientoPedidoUseCase @Inject constructor(private val seguimientoPedidoRepository: SeguimientoPedidoRepository) {
    suspend operator fun invoke():List<SeguimientoPedidoResponse>{
        return seguimientoPedidoRepository.getSeguimientoPedido()
    }
}