package com.example.applepsac.auth.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.viewmodel.SeguimientoPedidoViewModel

@Composable
fun listadoSeguimientoPedidos(seguimientoPedidoViewModel: SeguimientoPedidoViewModel = hiltViewModel()) {

    Scaffold() { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()){

            //val seguimientoPedidos = seguimientoPedidoViewModel.getSeguimientos()
            //val seguimientoPedidos = seguimientoPedidoViewModel.seguimientos.observeAsState(emptyList())
            val seguimientoPedidos by seguimientoPedidoViewModel.seguimientos.collectAsState()

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(seguimientoPedidos){ seguimiento ->
                    itemSeguimientos(seguimiento)
                }
            }

        }

    }

}

@Composable
fun itemSeguimientos(seguimientoPedidoResponse: SeguimientoPedidoResponse){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = seguimientoPedidoResponse.id.toString(), fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                //Text(text = seguimientoPedidoResponse.nombre)
                Text(text = seguimientoPedidoResponse.title)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = seguimientoPedidoResponse.body)
                //Text(text = seguimientoPedidoResponse.salario.toString())
            }
        }
    }
}