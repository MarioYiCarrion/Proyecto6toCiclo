package com.example.applepsac

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.applepsac.auth.view.DetallesdelPedido
import com.example.applepsac.auth.view.FaqyGuiasdeuso
import com.example.applepsac.auth.view.HistorialNotificaciones
import com.example.applepsac.auth.view.Notificaciones

//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
//import androidx.hilt.navigation.compose.hiltViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
@Composable
fun PantallaPrincipal(onExitClick: () -> Unit, nombreCliente: String) {

    //val seguimientoPedidoViewModel: SeguimientoPedidoViewModel = viewModel()

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarTitle = when (currentRoute) {
        "sugerencias" -> "Enviar Sugerencias"
        "rate" -> "Califícanos"
        "detallesp" -> "Detalles de Pedidos"
        "settings" -> "Configuraciones"
        "actualizaciones" -> "Actualizaciones del Sistema"
        "notifica" -> "Notificaciones"
        "historialnotifica" -> "Historial de Notificaciones"
        "faqyg" -> "FAQ y Guias de Uso"
        else -> "Pantalla Principal"
    }

    SetSystemBarsColor(color = Color(0xFF6200EE))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState, topBarTitle) },
        drawerContent = { DrawerContent(navController, onExitClick, scaffoldState, scope) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {
                pie(modifier = Modifier.fillMaxWidth())
            }
        }
        /*bottomBar = { BottomNavigationBar(navController) }*/
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues)
                .padding(bottom = 16.dp)
                .fillMaxSize()
        ) {
            composable("home") { MainContent(nombreCliente) }
            composable("settings") { SettingsScreen(navController) }
            composable("edit") { EditProfileScreen() }
            composable("contact") { ContactScreen() }
            composable("orders") { OrdersApp(navController = rememberNavController()) }//OrdersScreen(seguimientoPedidoViewModel)
            composable("rate") { CalificanosScreen(navController) }
            composable("sugerencias") { EnviarSugerencias(navController) }
            composable("actualizaciones") { HistorialActualizaciones() }
            composable("detallesp") { DetallesdelPedido() }
            composable("notifica") { Notificaciones() }
            composable("historialnotifica") { HistorialNotificaciones()}
            composable("faqyg") { FaqyGuiasdeuso()}
        }
    }
    }

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, title: String) {
    TopAppBar(
        title = { Text(title, fontWeight = FontWeight.Bold) },
        backgroundColor = Color(0xFF6200EE),
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        modifier = Modifier.statusBarsPadding()
    )
}

@Composable
fun DrawerContent(navController: NavHostController, onExitClick: () -> Unit, scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Menú",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            DrawerItem(icon = Icons.Default.Home, text = "Inicio", onClick = {
                navController.navigate("home")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.Place, text = "Seguimiento de Pedido", onClick = {
                navController.navigate("orders")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })

            DrawerItem(icon = Icons.Default.AddBusiness, text = "Detalles de Pedidos", onClick = {
                navController.navigate("detallesp")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })

            DrawerItem(icon = Icons.Default.Email, text = "Enviar Sugerencias", onClick = {
                navController.navigate("sugerencias")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.Star, text = "Califícanos", onClick = {
                navController.navigate("rate")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.Assignment, text = "FAQ y Guias de Uso", onClick = {
                navController.navigate("faqyg")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.AddAlert, text = "Notificaciones", onClick = {
                navController.navigate("notifica")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.Campaign, text = "Historial de Notificaciones", onClick = {
                navController.navigate("historialnotifica")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.Info, text = "Actualizaciones del Sistema", onClick = {
                navController.navigate("actualizaciones")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            DrawerItem(icon = Icons.Default.Settings, text = "Configuraciones", onClick = {
                navController.navigate("settings")
                scope.launch { scaffoldState.drawerState.close() } // Cierra el menú lateral
            })
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 48.dp, end = 16.dp)
        ) {
            DrawerItem(icon = Icons.Default.Close, text = "Salir", onClick = onExitClick)
        }
    }
}

@Composable
fun DrawerItem(icon: ImageVector, text: String, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick?.invoke() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333)
        )
    }
}
/*
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color(0xFF6200EE),
        contentColor = Color.White,
        modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = navController.currentDestination?.route == "home",
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Configuración") },
            label = { Text("Configuración") },
            selected = navController.currentDestination?.route == "settings",
            onClick = { navController.navigate("settings") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Email, contentDescription = "Contáctanos") },
            label = { Text("Contáctanos") },
            selected = navController.currentDestination?.route == "contact",
            onClick = { navController.navigate("contact") }
        )
    }
}
*/
@Composable
fun MainContent(nombreCliente: String) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido $nombreCliente!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("edit") }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_persona),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text("EDITAR PERFIL", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun ContactScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Contact Screen", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
fun SetSystemBarsColor(color: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as androidx.activity.ComponentActivity).window
        window.statusBarColor = color.toArgb()
        window.navigationBarColor = color.toArgb()
        val windowInsetsController = WindowCompat.getInsetsController(window, view)
        windowInsetsController.isAppearanceLightStatusBars = false
        windowInsetsController.isAppearanceLightNavigationBars = false
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaPrincipal() {
    PantallaPrincipal(
        onExitClick = {},
        nombreCliente = "Cliente"
    )
}


@Composable
fun pie(modifier: Modifier){
    Column(modifier = modifier.fillMaxWidth()){
        HorizontalDivider(
            Modifier
                .background(Color(0x0FAFAEAE))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(15.dp))



    }
}

