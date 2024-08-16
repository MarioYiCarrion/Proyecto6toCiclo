package com.example.applepsac.auth.data.network.response

data class DetallePedidoResponse(
    val t_detalle_pedido_id : Int,
    val orden: Int,
    val descripcion_estado: String,
    val comentario: String
)

