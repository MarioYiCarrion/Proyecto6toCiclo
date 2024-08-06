package com.example.applepsac.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applepsac.auth.data.network.retroclient.IActualizacionClient
import com.example.applepsac.auth.data.network.response.Actualizacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActualizacionesViewModel @Inject constructor(
    private val actualizacionClient: IActualizacionClient
) : ViewModel() {

    private val _actualizaciones = MutableStateFlow<List<Actualizacion>>(emptyList())
    val actualizaciones: StateFlow<List<Actualizacion>> get() = _actualizaciones

    init {
        fetchActualizaciones()
    }

    private fun fetchActualizaciones() {
        viewModelScope.launch {
            try {
                val response = actualizacionClient.getActualizaciones()
                _actualizaciones.value = response.results
            } catch (e: Exception) {
                println("Error al obtener actualizaciones: ${e.message}")
                _actualizaciones.value = emptyList() // O maneja el error de otra manera
            }
        }
    }
}