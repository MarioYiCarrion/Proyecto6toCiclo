package com.example.applepsac.auth.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applepsac.auth.viewmodel.SeguimientoPedidoViewModel

@Composable
fun pantallaSeguimientoPedidoScreen(seguimientoPedidoViewModel: SeguimientoPedidoViewModel){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        listadoSeguimientoPedidos(seguimientoPedidoViewModel = seguimientoPedidoViewModel)
    }
}