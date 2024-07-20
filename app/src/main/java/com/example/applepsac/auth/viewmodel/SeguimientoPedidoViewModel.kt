package com.example.applepsac.auth.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.domain.SeguimientoPedidoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
//import androidx.lifecycle.ViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
@HiltViewModel
class SeguimientoPedidoViewModel @Inject constructor(private val seguimientoPedidoUseCase: SeguimientoPedidoUseCase):ViewModel() {

    private val _seguimientos = mutableStateOf<List<SeguimientoPedidoResponse>>(emptyList())
    //private val _posts = MutableLiveData<List<PostResponse>>(emptyList())

    val seguimientos get() = _seguimientos


    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            val postList = seguimientoPedidoUseCase()
            _seguimientos.value = postList
        }
    }

    fun getSeguimientos(): List<SeguimientoPedidoResponse> {
        //viewModelScope.launch { Log.i("POSTLIST",postUseCase().toString()) }
        return seguimientos.value
    }
}