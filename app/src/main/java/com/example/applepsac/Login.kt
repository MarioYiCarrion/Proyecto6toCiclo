package com.example.applepsac

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.google.firebase.annotations.concurrent.Background
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ){


            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.splash1),
                null,
                Modifier.size(280.dp),
                tint = Color.White
            )

            UsernameField(username) { username = it }
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(password) { password = it }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = {  }) {
                    Text("Olvidaste tu Contraseña?", color = Color.Blue, fontSize = 17.sp)
                }
            }


            Spacer(modifier = Modifier.height(32.dp))
            LoginButton {
                loginUser(auth, username, password, navController)
            }


            androidx.compose.material3.Divider(
                color = Color.White.copy(alpha = 0.3f),
                thickness = 3.dp,
                modifier = Modifier.padding(top = 48.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.material3.Text(" No tienes cuenta?", color = Color.White, fontSize = 17.sp)
                androidx.compose.material3.TextButton(onClick = {navController.navigate("registrarCliente")}) {
                    androidx.compose.material3.Text("REGISTRATE AQUI!", fontWeight = FontWeight.Bold,color = Color.Blue, fontSize = 17.sp)
                }
            }
        }


    }
}

private fun loginUser(auth: FirebaseAuth, username: String, password: String, navController: NavHostController) {
    auth.signInWithEmailAndPassword(username, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
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
        label = { Text("Correo Electrónico", fontWeight = FontWeight.Bold, color = Color.White,fontSize = 20.sp) },
        leadingIcon = { Icon(imageVector = Icons.Default.Person,tint = Color.White, contentDescription = null) },

        modifier = Modifier.fillMaxWidth(),
        singleLine = true,

        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,

        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray
        )
    )
}

@Composable
fun PasswordField(password: String, onValueChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text("Contraseña", fontWeight = FontWeight.Bold, color = Color.White,fontSize = 20.sp) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock,tint = Color.White, contentDescription = null) },
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Done else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null, tint = Color.White)
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
        }),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray
        )
    )
}

@Composable
fun LoginButton(onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Iniciar Sesión", fontSize = 22.sp)
    }
}
/*
@Composable
fun LoginButton(onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Iniciar Sesión",fontSize = 24.sp)
    }
}
*/
@Composable
fun RegistrarButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ){
        Text(text = "Registrar Cliente", fontSize = 18.sp)
    }
}

@Composable
fun RecuperarClaveButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ){
        Text(text = "Recuperar Clave", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppLepsacTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}
