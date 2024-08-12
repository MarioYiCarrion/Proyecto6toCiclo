package com.example.applepsac.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetallePedidoScreen(id: String?) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Fondo gris claro
        ) {
            // Encabezado
            Text(
                text = "Detalle del Pedido $id",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, color = Color.Black),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Aquí puedes agregar más detalles del pedido
            // Ejemplo: Información del pedido, estado, etc.
            OrderDetailSection(orderId = id)
        }
    }
}

@Composable
fun OrderDetailSection(orderId: String?) {
    // Usamos remember aquí para evitar recalculaciones innecesarias, pero sin incluir lógica relacionada con la composición
    val orderDetails by remember(orderId) {
        mutableStateOf(getOrderDetails(orderId))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalles del Pedido",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, color = Color.Black),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Aquí se muestran los detalles del pedido
        orderDetails?.let {
            Text(
                text = "ID del Pedido: ${it.id}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Fecha: ${it.date}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Estado: ${it.status}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        } ?: run {
            Text(
                text = "No se encontraron detalles para el pedido.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

// Simulación de una función para obtener detalles del pedido. Reemplázala con la lógica real.
data class OrderDetails(val id: String, val date: String, val status: String)

fun getOrderDetails(orderId: String?): OrderDetails? {
    // Aquí deberías implementar la lógica para obtener los detalles del pedido.
    // Esta función es solo un ejemplo y debe ser reemplazada con la lógica real.
    return if (orderId != null) {
        OrderDetails(
            id = orderId,
            date = "2024-08-11",
            status = "En proceso"
        )
    } else {
        null
    }
}
