package com.example.applepsac.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applepsac.auth.data.network.request.HistorialNotificacion
import com.example.applepsac.auth.data.network.retroclient.HistorialNotificacionesApi
import com.example.applepsac.auth.data.network.retroclient.NotificacionesApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HistorialNotificacionesViewModel : ViewModel() {
    var historialNotificaciones by mutableStateOf<List<HistorialNotificacion>>(emptyList())
        private set

    private val api: HistorialNotificacionesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nodejs-mysql-restapi-test-production-895d.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(HistorialNotificacionesApi::class.java)
    }

    fun cargarHistorialNotificaciones(userId: Int) {
        viewModelScope.launch {
            try {
                val response = api.getHistorialNotificaciones(userId)
                historialNotificaciones = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}