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
import com.example.applepsac.auth.data.network.response.SeguimientoPedidoResponse
import com.example.applepsac.auth.viewmodel.SeguimientoPedidoViewModel


@Composable
fun listadoSeguimientoPedidos(navController: NavController, seguimientoPedidoViewModel: SeguimientoPedidoViewModel = hiltViewModel()) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            InfoCard()
            val seguimientoPedidos by seguimientoPedidoViewModel.seguimientos.collectAsState()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(seguimientoPedidos) { seguimiento ->
                    ItemSeguimientos(seguimiento, navController)
                }
            }
        }
    }
}

@Composable
fun InfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = Color(0xFF42A5F5)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Podrás ver el detalle de tus pedidos en esta sección en unos minutos. Ingresa al detalle para conocer su estado.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ItemSeguimientos(seguimientoPedidoResponse: SeguimientoPedidoResponse, navController: NavController) {
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
                        text = "Nro. ${seguimientoPedidoResponse.id}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Entrega: ${seguimientoPedidoResponse.comentarios}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Descripción: ${seguimientoPedidoResponse.descripcion}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Estado: ${seguimientoPedidoResponse.estado}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, color = Color.Gray)
                    )
                }
                TextButton(onClick = {
                    navController.navigate("detallePedido/${seguimientoPedidoResponse.id}")

                }) {
                    Text(text = "Ver detalle", color = Color(0xFF42A5F5))
                }
            }
        }
    }
}
