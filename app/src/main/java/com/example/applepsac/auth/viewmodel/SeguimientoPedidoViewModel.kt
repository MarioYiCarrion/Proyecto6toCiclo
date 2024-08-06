package com.example.applepsac.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.domain.SeguimientoPedidoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeguimientoPedidoViewModel @Inject constructor(
    private val seguimientoPedidoUseCase: SeguimientoPedidoUseCase
) : ViewModel() {

    private val _seguimientos = MutableLiveData<List<SeguimientoPedidoResponse>>(emptyList())
    val seguimientos get() = _seguimientos

    init {
        fetchSeguimientos()
    }

    private fun fetchSeguimientos() {
        viewModelScope.launch {
            val seguimientosList = seguimientoPedidoUseCase()
            Log.i("OJO",seguimientosList.toString())
            _seguimientos.value = seguimientosList
        }
    }

    fun getSeguimientos(): List<SeguimientoPedidoResponse>? {
        return seguimientos.value
    }
}