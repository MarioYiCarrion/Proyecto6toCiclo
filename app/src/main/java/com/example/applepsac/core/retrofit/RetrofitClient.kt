package com.example.applepsac.core.retrofit

import com.example.applepsac.auth.data.network.retroclient.ComentarioApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://nodejs-mysql-restapi-test-production-895d.up.railway.app/api/"

    val instance: ComentarioApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComentarioApi::class.java)
    }
}