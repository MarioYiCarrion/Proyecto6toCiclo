package com.example.applepsac.core.retrofit

import com.example.applepsac.auth.data.network.retroclient.DetallePedidoClient
import com.example.applepsac.auth.data.network.retroclient.IActualizacionClient
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
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSeguimientoPedidoClient(retrofit: Retrofit):SeguimientoPedidoClient{
        return retrofit.create(SeguimientoPedidoClient::class.java)
    }

    @Singleton
    @Provides
    fun provideDetallePedidoClient(retrofit: Retrofit): DetallePedidoClient {
        return retrofit.create(DetallePedidoClient::class.java)
    }

    @Singleton
    @Provides
    fun provideActualizacionClient(retrofit: Retrofit): IActualizacionClient {
        return retrofit.create(IActualizacionClient::class.java)
    }
}