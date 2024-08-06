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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Data class para representar una actualización
data class Actualizacion(
    val titulo: String,
    val mensaje: String,
    val fecha: String
)

// ViewModel para manejar la lógica de negocio
class ActualizacionesViewModel : ViewModel() {
    private val _actualizaciones = MutableStateFlow<List<Actualizacion>>(emptyList())
    val actualizaciones: StateFlow<List<Actualizacion>> = _actualizaciones

    init {
        // Información de actualizaciones iniciales, se cargan ficticios hasta crear en la bd de firebase o api
        _actualizaciones.value = listOf(
            Actualizacion("Actualización de Política", "Se han actualizado las políticas de privacidad.", "14-10-2023"),
            Actualizacion("Cambio en Servicios", "El servicio de atención al cliente ahora está disponible 24/7.", "14-01-2024"),
            Actualizacion("Nuevo Producto", "Se ha lanzado un nuevo producto.", "05-04-2024"),
            Actualizacion("Mantenimiento Programado", "Habrá un mantenimiento programado el 25 de julio.", "18-07-2024"),
            /// se puede agregar mas para probar el lazycolumn
        )
    }

    // Función pública para añadir actualizaciones (solo para el preview)
    fun agregarActualizaciones(actualizaciones: List<Actualizacion>) {
        _actualizaciones.value = actualizaciones
    }
}

@Composable
fun HistorialActualizaciones(viewModel: ActualizacionesViewModel = viewModel()) {
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
            Text(actualizacion.mensaje)
            Spacer(modifier = Modifier.height(8.dp))
            Text(actualizacion.fecha, style = MaterialTheme.typography.body2, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistorialActualizaciones() {
    val mockViewModel = ActualizacionesViewModel().apply {
        agregarActualizaciones(listOf(
            Actualizacion("Actualización de Política", "Se han actualizado las políticas de privacidad.", "14-10-2023"),
            Actualizacion("Cambio en Servicios", "El servicio de atención al cliente ahora está disponible 24/7.", "14-01-2024"),
            Actualizacion("Nuevo Producto", "Se ha lanzado un nuevo producto.", "05-04-2024"),
            Actualizacion("Mantenimiento Programado", "Habrá un mantenimiento programado el 25 de julio.", "18-07-2024"),
        ))
    }
    HistorialActualizaciones(viewModel = mockViewModel)
}
