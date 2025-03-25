package com.example.applepsac.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.applepsac.auth.data.network.response.DetallePedidoResponse
import com.example.applepsac.auth.viewmodel.DetallePedidoViewModel



@Composable
fun detallePedido(navController: NavController, pedidoId: String?, detallePedidoViewModel: DetallePedidoViewModel = hiltViewModel()) {
    pedidoId?.let {
        detallePedidoViewModel.loadDetallePedido(it)
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            val detallePedido by detallePedidoViewModel.detalleSeguimiento.collectAsState()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(detallePedido) { detalle ->
                    itemDetalle(detalle)
                }
            }
        }
    }
}



@Composable
fun itemDetalle(detallePedidoResponse: DetallePedidoResponse) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Nro. Producto ${detallePedidoResponse.t_detalle_pedido_id}", // Aquí ya es Int, no String
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Nro. ${detallePedidoResponse.orden}", // Aquí ya es Int, no String
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Entrega: ${detallePedidoResponse.descripcion_estado}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Descripcion: ${detallePedidoResponse.comentario}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, color = Color.Gray)
                    )
                }
            }
        }
    }
}


