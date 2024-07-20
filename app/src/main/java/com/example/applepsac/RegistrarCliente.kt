package com.example.applepsac

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun RegistrarUsuario(navController: NavHostController? = null) {
    var dni by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    val dniFocusRequester = FocusRequester()
    val nombreFocusRequester = FocusRequester()
    val apellidoPaternoFocusRequester = FocusRequester()
    val apellidoMaternoFocusRequester = FocusRequester()
    val direccionFocusRequester = FocusRequester()
    val celularFocusRequester = FocusRequester()
    val correoFocusRequester = FocusRequester()
    val claveFocusRequester = FocusRequester()
    val buttonFocusRequester = FocusRequester()

    val auth = if (navController != null) FirebaseAuth.getInstance() else null
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        dniFocusRequester.requestFocus()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Registrar Usuario",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            TextField(
                value = dni,
                onValueChange = { dni = it },
                label = { Text("DNI") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { nombreFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(dniFocusRequester)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { apellidoPaternoFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(nombreFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = apellidoPaterno,
                onValueChange = { apellidoPaterno = it },
                label = { Text("Apellido Paterno") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { apellidoMaternoFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(apellidoPaternoFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = apellidoMaterno,
                onValueChange = { apellidoMaterno = it },
                label = { Text("Apellido Materno") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { direccionFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(apellidoMaternoFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { celularFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(direccionFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = celular,
                onValueChange = { celular = it },
                label = { Text("Celular") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { correoFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(celularFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { claveFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(correoFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text("Clave") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { buttonFocusRequester.requestFocus() }),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(claveFocusRequester),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(16.dp)) // Espacio adicional para asegurar que el botón no quede cubierto
        }
        item {
            Button(
                onClick = {
                    auth?.createUserWithEmailAndPassword(correo, clave)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                val userId = user?.uid ?: ""

                                val database = FirebaseDatabase.getInstance()
                                val ref = database.getReference("clientes")

                                val userData = hashMapOf(
                                    "dni" to dni,
                                    "nombre" to nombre,
                                    "apellidoPaterno" to apellidoPaterno,
                                    "apellidoMaterno" to apellidoMaterno,
                                    "direccion" to direccion,
                                    "celular" to celular,
                                    "correo" to correo,
                                    "clave" to clave
                                )

                                ref.child(userId).setValue(userData)
                                    .addOnCompleteListener { dbTask ->
                                        if (dbTask.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "Usuario registrado exitosamente",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController?.navigate("loginScreen")
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Error al registrar usuario en la base de datos",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                            } else {
                                Toast.makeText(
                                    context,
                                    "Error al registrar usuario: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(buttonFocusRequester)
            ) {
                Text("Registrar")
            }
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrarUsuarioPreview() {
    RegistrarUsuario()
}
