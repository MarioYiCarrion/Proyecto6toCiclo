package com.example.applepsac

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applepsac.auth.data.network.response.Actualizacion
import com.example.applepsac.auth.viewmodel.ActualizacionesViewModel

@Composable
fun HistorialActualizaciones(viewModel: ActualizacionesViewModel = hiltViewModel()) {
    val actualizaciones by viewModel.actualizaciones.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Actualizaciones") },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(actualizaciones) { actualizacion ->
                    ActualizacionItem(actualizacion)
                }
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
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(actualizacion.titulo, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(actualizacion.descripcion)
            Spacer(modifier = Modifier.height(8.dp))
            Text(actualizacion.fecha.substring(0, 10), style = MaterialTheme.typography.body2, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistorialActualizaciones() {
    // Implementación de vista previa para Composable
}


