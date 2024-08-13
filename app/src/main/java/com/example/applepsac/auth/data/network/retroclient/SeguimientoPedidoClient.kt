package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import retrofit2.Response
import retrofit2.http.GET

interface SeguimientoPedidoClient {
    //@GET("/posts")
    @GET("posiciones/cliente/"+1)
    //suspend fun getPosts(): Response<List<PostResponse>>
    suspend fun getSeguimientoPedido(): Response<List<SeguimientoPedidoResponse>>
}