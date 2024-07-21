package com.example.applepsac.core.retrofit

import com.example.applepsac.auth.data.network.retroclient.SeguimientoPedidoClient
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://nodejs-mysql-restapi-test-production-895d.up.railway.app/api/")
            //.baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    //fun provideSeguimientoPedidoClient(retrofit: Retrofit):PostClient{
    //    return retrofit.create(PostClient::class.java)
    //}
    fun provideSeguimientoPedidoClient(retrofit: Retrofit):SeguimientoPedidoClient{
        return retrofit.create(SeguimientoPedidoClient::class.java)
    }
}