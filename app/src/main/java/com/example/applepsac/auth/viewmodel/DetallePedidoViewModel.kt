package com.example.applepsac.auth.viewmodel

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import com.example.applepsac.auth.domain.DetallePedidoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallePedidoViewModel @Inject constructor(
    private val detallePedidoUseCase: DetallePedidoUseCase
): ViewModel() {
    private val _detalleSeguimiento = MutableStateFlow<List<DetallePedidoResponse>>(emptyList())
    val detalleSeguimiento: StateFlow<List<DetallePedidoResponse>> get() = _detalleSeguimiento

    init {
        fetchDetalleSeguimiento()
    }

    private fun fetchDetalleSeguimiento(){
        viewModelScope.launch {
            val detallePedidoList = detallePedidoUseCase()
            Log.i("OJO",detallePedidoList.toString())
            _detalleSeguimiento.value = detallePedidoList

        }
    }
}