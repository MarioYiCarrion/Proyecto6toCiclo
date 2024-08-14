package com.example.applepsac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.applepsac.auth.view.detallePedido
import com.example.applepsac.auth.view.listadoSeguimientoPedidos
import com.example.applepsac.auth.viewmodel.SeguimientoPedidoViewModel
import com.example.applepsac.ui.theme.AppLepsacTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            LoginScreen(navController = navController, context = LocalContext.current)
        }
        composable("detallesp") {
            listadoSeguimientoPedidos(navController)
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
                            // Manejar errores si es necesario
                        }
                    })
                }
            }

            PantallaPrincipal(
                onExitClick = {
                    navController.navigate("loginScreen") {
                        popUpTo("pantallaPrincipal") { inclusive = true }
                    }
                },
                nombreCliente = nombreCliente.value
            )
        }
        composable("RegistrarCliente") {
            RegistrarUsuario(navController = navController)
        }
        composable("ConsultaPedidos") {
            OrdersApp(navController = navController)
        }
        composable("PasswordRecoveryScreen") {
            PasswordRecoveryScreen(navController = navController)
        }
        composable("HistorialActualizaciones") {
            HistorialActualizaciones()
        }
        composable(
            route = "detallePedido/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                detallePedido(navController,id)
            }
        }

    }
}
