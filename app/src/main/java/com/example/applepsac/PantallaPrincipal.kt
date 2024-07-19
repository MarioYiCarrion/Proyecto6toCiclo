package com.example.applepsac

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat


@Composable
fun PantallaPrincipal(navController: NavHostController, onExitClick: () -> Unit, nombreCliente: String) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    SetSystemBarsColor(color = Color(0xFF6200EE))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        drawerContent = { DrawerContent(navController, onExitClick) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues)
                .padding(bottom = 16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainContent(nombreCliente)
            }
        }
    }
}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text("Pantalla Principal", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
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
fun DrawerContent(navController: NavHostController, onExitClick: () -> Unit) {
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
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            DrawerItem(icon = Icons.Default.Home, text = "Inicio")
            DrawerItem(icon = Icons.Default.Place, text = "Seguimiento de Pedido", onClick = { navController.navigate("orders") })
            DrawerItem(icon = Icons.Default.Settings, text = "Configuraciones")
            DrawerItem(icon = Icons.Default.Settings, text = "Detalles del Pedido")
            DrawerItem(icon = Icons.Default.Settings, text = "Detalles del Pedido")
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 48.dp, end = 16.dp) // Añadido padding en la parte inferior
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


@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color(0xFF6200EE),
        contentColor = Color.White,
        modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { /* Acción cuando se selecciona Home */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = false,
            onClick = { /* Acción cuando se selecciona Settings */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Email, contentDescription = "Contactanos") },
            label = { Text("Contactanos") },
            selected = false,
            onClick = { /* Acción cuando se selecciona Contactanos */ }
        )
    }
}

@Composable
fun MainContent(nombreCliente: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp) // Optional extra padding for content
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
        navController = rememberNavController(),
        onExitClick = {},
        nombreCliente = "Cliente"
    )
}
