package com.example.applepsac.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applepsac.auth.data.network.request.Notificacion
import com.example.applepsac.auth.data.network.retroclient.NotificacionesApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotificacionesViewModel : ViewModel() {
    var notificaciones by mutableStateOf<List<Notificacion>>(emptyList())
        private set

    private val api: NotificacionesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nodejs-mysql-restapi-test-production-895d.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(NotificacionesApi::class.java)
    }

    fun cargarNotificaciones(userId: Int) {
        viewModelScope.launch {
            try {
                val response = api.getNotificaciones(userId)
                notificaciones = response.results // Mapea los resultados obtenidos
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}