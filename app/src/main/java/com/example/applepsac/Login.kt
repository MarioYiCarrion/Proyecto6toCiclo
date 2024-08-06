package com.example.applepsac

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.applepsac.ui.theme.AppLepsacTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavHostController, context: Context) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // Load saved credentials
    LaunchedEffect(Unit) {
        username = sharedPreferences.getString("username", "") ?: ""
        password = sharedPreferences.getString("password", "") ?: ""
        rememberMe = sharedPreferences.getBoolean("remember_me", false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "Iniciar Sesión",
            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE)),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        UsernameField(username) { username = it }
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(password) { password = it }
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { checked ->
                    rememberMe = checked
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Recordar credenciales", style = TextStyle(fontSize = 14.sp))
        }
        Spacer(modifier = Modifier.height(24.dp))
        LoginButton {
            loginUser(auth, username, password, navController, context, rememberMe)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Text links for registration and password recovery
        TextButton(onClick = { navController.navigate("RegistrarCliente") }) {
            Text("Regístrate", color = Color(0xFF6200EE))
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("PasswordRecoveryScreen") }) {
            Text("Olvidaste tu contraseña?", color = Color(0xFF6200EE))
        }
    }
}

private fun loginUser(
    auth: FirebaseAuth,
    username: String,
    password: String,
    navController: NavHostController,
    context: Context,
    rememberMe: Boolean
) {
    auth.signInWithEmailAndPassword(username, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Save credentials if rememberMe is true
                if (rememberMe) {
                    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()) {
                        putString("username", username)
                        putString("password", password)
                        putBoolean("remember_me", rememberMe)
                        apply()
                    }
                } else {
                    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()) {
                        remove("username")
                        remove("password")
                        remove("remember_me")
                        apply()
                    }
                }
                // Inicio de sesión exitoso, navegar a la pantalla principal
                navController.navigate("pantallaPrincipal")
            } else {
                // Error en el inicio de sesión, mostrar mensaje o manejar el error
                // task.exception?.message puede ser útil para obtener detalles del error
            }
        }
}

@Composable
fun UsernameField(username: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = username,
        onValueChange = onValueChange,
        label = { Text("Correo Electrónico") },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun PasswordField(password: String, onValueChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text("Contraseña") },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Done else Icons.Filled.Clear
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            // Llamar al inicio de sesión aquí
        })
    )
}

@Composable
fun LoginButton(onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE))
    ) {
        Text(text = "Iniciar Sesión", fontSize = 18.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppLepsacTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController, context = LocalContext.current)
    }
}
