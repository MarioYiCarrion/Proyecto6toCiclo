package com.example.applepsac.auth.data.network.retroclient

import com.example.applepsac.auth.data.network.request.Comentario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ComentarioApi {
    @POST("comentarios")
    fun enviarComentario(@Body comentario: Comentario): Call<Void>
}