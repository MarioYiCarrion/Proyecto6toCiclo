package com.example.applepsac.auth.data.network.service

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import com.example.applepsac.auth.data.network.retroclient.DetallePedidoClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetallePedidoService @Inject constructor  (private val detallePedidoClient: DetallePedidoClient){
    suspend fun getDetallePedido(): List<DetallePedidoResponse>{
        return withContext(Dispatchers.IO){
            val response = detallePedidoClient.getDetallePedido()
            if (response.isSuccessful){
                response.body() ?: throw IllegalStateException("Response body is null")
            }else{
                throw IllegalStateException("Respuesta no exitosa: ${response.code()}")
            }
        }
    }
}