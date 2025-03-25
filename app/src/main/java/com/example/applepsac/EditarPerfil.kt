package com.example.applepsac

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

@Composable
fun EditProfileScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    var nombre by remember { mutableStateOf("") }
    var apPaterno by remember { mutableStateOf("") }
    var apMaterno by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf(currentUser?.email ?: "") }
    var celular by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var nuevaContrasena by remember { mutableStateOf("") }
    var mensajeRespuesta by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Editar Perfil",
            style = MaterialTheme.typography.h5.copy(fontSize = 24.sp),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apPaterno,
            onValueChange = { apPaterno = it },
            label = { Text("Apellido Paterno") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Apellido Paterno") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apMaterno,
            onValueChange = { apMaterno = it },
            label = { Text("Apellido Materno") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Apellido Materno") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = celular,
            onValueChange = { celular = it },
            label = { Text("Celular") },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Celular") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            leadingIcon = { Icon(Icons.Default.Home, contentDescription = "Dirección") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nuevaContrasena,
            onValueChange = { nuevaContrasena = it },
            label = { Text("Nueva Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Nueva Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val resultado = enviarDatosAPI(nombre, apPaterno, apMaterno, correo, celular, direccion, nuevaContrasena)
                    mensajeRespuesta = resultado

                    if (resultado.contains("éxito", true)) {
                        mostrarDialogo = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("Guardar Cambios", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (mensajeRespuesta.isNotEmpty()) {
            Text(
                text = mensajeRespuesta,
                color = if (mensajeRespuesta.contains("éxito", true)) Color.Green else Color.Red,
                fontSize = 14.sp
            )
        }

        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("Éxito") },
                text = { Text("Tu perfil se ha actualizado correctamente.") },
                confirmButton = {
                    Button(
                        onClick = {
                            mostrarDialogo = false
                            navController.navigate("home")
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}

fun enviarDatosAPI(
    nombre: String,
    apPaterno: String,
    apMaterno: String,
    correo: String,
    celular: String,
    direccion: String,
    contrasena: String
): String {
    val url = "https://nodejs-mysql-restapi-test-production-895d.up.railway.app/api/usuariosporcorreo/$correo"
    val json = JSONObject().apply {
        put("nombre", nombre)
        put("ap_paterno", apPaterno)
        put("ap_materno", apMaterno)
        put("celular", celular)
        put("direccion", direccion)
        put("password", contrasena)
    }
    val client = OkHttpClient()
    val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder()
        .url(url)
        .put(requestBody)
        .build()

    return try {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            "Perfil actualizado con éxito"
        } else {
            "Error al actualizar: ${response.code}"
        }
    } catch (e: Exception) {
        "Error en la conexión"
    }
}
