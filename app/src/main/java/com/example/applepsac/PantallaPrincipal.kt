package com.example.applepsac

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.applepsac.auth.view.FAQScreen

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
                /*pie(modifier = Modifier.fillMaxWidth())*/
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
            composable("faqyg") { FAQScreen()}
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
            DrawerItem(icon = Icons.Default.Home, text = "Home", onClick = {
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
    Column {

        Content()
    }

/*
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
    }*/
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

/*
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

*/

@Composable
fun Content() {
    Column() {
        Spacer(modifier = Modifier.height(12.dp))
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        Promotions()
        Spacer(modifier = Modifier.height(16.dp))
        CategorySection()
        Spacer(modifier = Modifier.height(16.dp))
        BestSellerSection()
    }
}

@Composable
fun Header() {
    Card(
        Modifier
            .height(64.dp)
            .padding(horizontal = 16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            QrButton()

            VerticalDivider()
            Row(Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable { }
                .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_money), contentDescription = "", tint = Color(0xFF6FCF97))
                Column(Modifier.padding(8.dp)) {
                    Text(text = "S/3,73", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "Dolar", color = MaterialTheme.colors.primary, fontSize = 12.sp)
                }
            }

            VerticalDivider()
            Row(Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable { }
                .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_coin), contentDescription = "", tint = MaterialTheme.colors.primary)
                Column(Modifier.padding(8.dp)) {
                    Text(text = "S/4,05", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "Euro", color = MaterialTheme.colors.primary, fontSize = 12.sp)
                }
            }
        }
    }
}


@Composable
fun QrButton() {
    IconButton(
        onClick = {},
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.tipodecambio),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}


@Composable
fun VerticalDivider() {
    Divider(
        color = Color(0xFFF1F1F1),
        modifier = Modifier
            .width(1.dp)
            .height(32.dp)
    )
}


@Composable
fun Promotions() {
    LazyRow(
        Modifier.height(160.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.electricidad3),
                title = "Por todo Agosto",
                subtitle = "En Tienda",
                header = "15% Dscto.",
                backgroundColor = MaterialTheme.colors.primary
            )
        }
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.targeta1),
                title = "Paga Con tu Tarjeta",
                subtitle = "Interbank",
                header = "20% Dscto."
                /*backgroundColor = Color(0xff6EB6F5)*/
            )
        }
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.seguimiento1),
                title = "Con Nosotros",
                subtitle = "Tus Pedidos",
                header = "Siempre llegaran Seguros"
                /*backgroundColor = Color(0xff6EB6F5)*/
            )
        }
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.contactos1),
                title = "Siempre Resolveremos tus dudas",
                subtitle = "Contactanos",
                header = "info@lepsac.net.pe",
//                backgroun20%dColor = Color(0xff6EB6F5)
            )
        }
    }
}

@Composable
fun PromotionItem(
    title: String = "",
    subtitle: String = "",
    header: String = "",
    backgroundColor: Color = Color.Transparent,
    imagePainter: Painter
) {
    Card(
        Modifier
            .width(350.dp)
            .height(200.dp), // Ajusta la altura según tus necesidades
        shape = RoundedCornerShape(10.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ) {
        Box {
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = title, fontSize = 14.sp, color = Color.White)
                Text(text = subtitle, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = header, fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CategorySection() {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Categorias", style = MaterialTheme.typography.h6)
            TextButton(onClick = {}) {
                Text(text = "Más..", color = MaterialTheme.colors.primary)
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryButton(
                text = "Seguimiento de Pedido",
                icon = painterResource(id = R.drawable.seguimiento),
                backgroundColor = Color(0xFFEF5A6F)
            )
            CategoryButton(
                text = "Detalles de Pedidos",
                icon = painterResource(id = R.drawable.detalles),
                backgroundColor = Color(0xFFFFF1DB)
            )
            CategoryButton(
                text = "Enviar Sugerencias",
                icon = painterResource(id = R.drawable.enviar),
                backgroundColor = Color(0xFFD4BDAC)
            )
            CategoryButton(
                text = "Calificanos",
                icon = painterResource(id = R.drawable.calificanos),
                backgroundColor = Color(0xFF536493)
            )
        }
    }
}

@Composable
fun CategoryButton(
    text: String = "",
    icon: Painter,
    backgroundColor: Color
) {
    Column(
        Modifier
            .width(72.dp)
            .clickable { }
    ) {
        Box(
            Modifier
                .size(72.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(18.dp)
        ) {
            Image(painter = icon, contentDescription = "", modifier = Modifier.fillMaxSize())
        }
        Text(text = text, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 12.sp)
    }
}

@Composable
fun BestSellerSection() {
    Column() {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Lo Más Vendido", style = MaterialTheme.typography.h6)
            TextButton(onClick = {}) {
                Text(text = "Más..", color = MaterialTheme.colors.primary)
            }
        }

        BestSellerItems()
    }
}


@Composable
fun BestSellerItems() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            BestSellerItem(
                imagePainter = painterResource(id = R.drawable.mas1),
                title = "P1-25/I2/SVB/HI11 207297 0001457889 EATON ELECTRIC Interruptor General 3 polos + 1 NO + 1 NC 25 A Montaje en..",
                price = "35.99",
                discountPercent = 15
            )
        }
        item {
            BestSellerItem(
                imagePainter = painterResource(id = R.drawable.masreload),
                title = "FAZ-C32/4 279064 EATON ELECTRIC Int. magnetotérmico FAZ, 32A, 4P, curva C",
                price = "25.99",
                discountPercent = 15
            )
        }
        item {
            BestSellerItem(
                imagePainter = painterResource(id = R.drawable.mas3),
                title = "DILEM12-10-G(24VDC) 127132 ELECTRIC Mini-Contactor de potencia Conexión a tornillo 3 polos + 1 NO 12 A",
                price = "25.99",
                discountPercent = 18
            )
        }
        item {
            BestSellerItem(
                imagePainter = painterResource(id = R.drawable.mas4),
                title = "DILM150-XHI22 277950 XTCEXFBG22 ELECTRIC Bloque de contactos auxiliares 2 NO + 2 NC Montaje frontal Co.",
                price = "45.99",
                discountPercent = 20
            )
        }
        item {
            BestSellerItem(
                imagePainter = painterResource(id = R.drawable.mas5),
                title = "ZB12-6 278439 XTOB006BC1 ELECTRIC Relé de sobrecarga 4-6 A 1 NO + 1 NC Montaje directo en DILM7…12",
                price = "28.99",
                discountPercent = 20
            )
        }
    }
}

@Composable
fun BestSellerItem(
    imagePainter: Painter,
    title: String,
    price: String,
    discountPercent: Int
) {
    Card(
        modifier = Modifier
            .width(200.dp) // Tamaño fijo para el Card
            .height(250.dp), // Tamaño fijo para el Card
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "S/$price", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "-$discountPercent%", fontSize = 14.sp, color = Color.Red)
                }
            }
        }
    }
}