package com.example.applepsac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.applepsac.ui.theme.AppLepsacTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLepsacTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val database = Firebase.database
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") {
            LoginScreen(navController = navController)
        }
        composable("pantallaPrincipal") {
            val uid = FirebaseAuth.getInstance().currentUser?.uid
            val nombreCliente = remember { mutableStateOf("") }

            LaunchedEffect(uid) {
                uid?.let {
                    val clientRef = database.getReference("clientes/$uid")
                    clientRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val nombre = snapshot.child("nombre").getValue(String::class.java)
                            nombre?.let { nombreCliente.value = it }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Manejo de errores (opcional)
                        }
                    })
                }
            }

            PantallaPrincipal(
                onExitClick = { /* Implementa la lógica de salida si es necesario */ },
                nombreCliente = nombreCliente.value // Pasar nombreCliente como parámetro
            )
        }
        composable("RegistrarCliente") {
            RegistrarUsuario(navController = navController)
        }
        composable("detallesPedido/{pedidoId}") { backStackEntry ->
            val pedidoId = backStackEntry.arguments?.getString("pedidoId") ?: ""
            DetallesdelPedido(navController = navController, pedidoId = pedidoId)
        }
    }
}