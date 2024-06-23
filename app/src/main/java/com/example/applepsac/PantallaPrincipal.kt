package com.example.applepsac

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun PantallaPrincipal(onExitClick: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        drawerContent = { DrawerContent(onExitClick) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        MainContent(
            Modifier
                .padding(paddingValues)
                .padding(16.dp))
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
        }
    )
}

@Composable
fun DrawerContent(onExitClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 48.dp, start = 16.dp, end = 16.dp)
    ) {
        DrawerItem(icon = Icons.Default.Home, text = "Inicio")
        DrawerItem(icon = Icons.Default.Place, text = "Seguimiento de Pedido")
        DrawerItem(icon = Icons.Default.Close, text = "Salir", onClick = onExitClick)
    }
}

@Composable
fun DrawerItem(icon: ImageVector, text: String, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color(0xFFF1F1F1))
            .padding(8.dp)
            .clickable { onClick?.invoke() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color(0xFF6200EE),
        contentColor = Color.White
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
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Bienvenido a Lepsac!!!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
    }
}

