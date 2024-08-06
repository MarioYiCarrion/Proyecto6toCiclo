    package com.example.applepsac

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applepsac.auth.data.network.response.Actualizacion
import com.example.applepsac.auth.viewmodel.ActualizacionesViewModel

@Composable
fun HistorialActualizaciones(viewModel: ActualizacionesViewModel = hiltViewModel()) {
    val actualizaciones by viewModel.actualizaciones.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE4E6EB))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(actualizaciones) { actualizacion ->
                ActualizacionItem(actualizacion)
            }
        }
    }
}

@Composable
fun ActualizacionItem(actualizacion: Actualizacion) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(8.dp), // Sombra en el Card
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = actualizacion.titulo,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF343A40) // Texto oscuro
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = actualizacion.descripcion,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF343A40) // Texto oscuro
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = actualizacion.fecha.substring(0, 10),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF6C757D) // Texto gris
                )
            )
        }
    }
}
