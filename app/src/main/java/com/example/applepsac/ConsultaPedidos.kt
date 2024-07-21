package com.example.applepsac

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.material.TopAppBar
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


data class Pedido1(
    val id: String,
    val fecha: String,
    val estado: String,
    val imageResource: Int
)


@Composable
fun OrdersApp(navController: NavHostController) {
    val pedidos = listOf(
        Pedido1("#1234", "14/10/2014", "En progreso", R.drawable.motor),
        Pedido1("#23452", "14/10/2014", "En progreso", R.drawable.control_panel),
        Pedido1("#31352", "14/10/2014", "En progreso", R.drawable.pump)
    )

    var searchQuery by remember { mutableStateOf("") }

    Column {
        TopAppBar(title = { Text("Pedidos") })
        SearchBar(searchQuery) { searchQuery = it }
        LazyColumn {
            items(pedidos) { pedido ->
                PedidoItem(pedido)
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Buscar Pedidosxx") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}

@Composable
fun PedidoItem(pedido: Pedido1) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(pedido.imageResource),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "pedido ${pedido.id}", fontSize = 18.sp, color = Color.Black)
                Text(text = "fecha ${pedido.fecha}", fontSize = 14.sp, color = Color.Gray)
                Text(text = "estado: ${pedido.estado}", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrdersAppPreview() {
    OrdersApp(navController = rememberNavController())
}