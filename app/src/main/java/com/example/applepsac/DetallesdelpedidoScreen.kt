package com.example.applepsac

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.*

@Composable
fun DetallesdelPedido(navController: NavHostController, pedidoId: String) {
    var pedido by remember { mutableStateOf<Pedido?>(null) }

    LaunchedEffect(pedidoId) {
        val database = FirebaseDatabase.getInstance().reference.child("pedidos").child(pedidoId)
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pedido = snapshot.getValue(Pedido::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores si es necesario
            }
        })
    }

    pedido?.let {
        PedidoDetalleContent(pedido = it)
    } ?: run {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun PedidoDetalleContent(pedido: Pedido) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Detalles dexxl Pedido", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        Text("ID del Pedido: ${pedido.id}", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        Text("Fecha: ${pedido.fecha}", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        Text("Estado: ${pedido.estado}", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text("Productos:", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
        pedido.productos.forEach { producto ->
            Text("- ${producto.nombre}: ${producto.cantidad}", fontSize = 18.sp, modifier = Modifier.padding(bottom = 4.dp))
        }
    }
}

data class Pedido(
    val id: String = "",
    val fecha: String = "",
    val estado: String = "",
    val productos: List<Producto> = listOf()
)

data class Producto(
    val nombre: String = "",
    val cantidad: Int = 0
)
