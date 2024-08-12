package com.example.applepsac

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.applepsac.auth.data.network.request.RegistrarRequest
import com.example.applepsac.auth.data.network.retroclient.IRegistrarUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    var isLoading by remember { mutableStateOf(false) }

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
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        dniFocusRequester.requestFocus()
    }

    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://nodejs-mysql-restapi-test-production-895d.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val registrarService = retrofit.create(IRegistrarUsuario::class.java)

    fun mostrarSnackbar(mensaje: String, esError: Boolean, duracion: Long = 2000L) {
        val navControllerSnapshot = navController ?: return

        coroutineScope.launch {
            val result = snackbarHostState.showSnackbar(
                message = mensaje,
                actionLabel = if (!esError) "OK" else null,
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
            delay(duracion)
            if (!esError) {
                navControllerSnapshot.navigate("loginScreen") {
                    popUpTo("loginScreen") { inclusive = true }
                }
            } else if (result == SnackbarResult.ActionPerformed) {
                navControllerSnapshot.navigate("loginScreen") {
                    popUpTo("loginScreen") { inclusive = true }
                }
            }
        }
    }

    fun validarCampos(): Boolean {
        return when {
            dni.isEmpty() || nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() ||
                    direccion.isEmpty() || celular.isEmpty() || correo.isEmpty() || clave.isEmpty() -> {
                mostrarSnackbar("Todos los campos deben estar completos.", true)
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> {
                mostrarSnackbar("El correo no tiene un formato válido.", true)
                false
            }
            clave.length < 6 -> {
                mostrarSnackbar("La clave debe tener al menos 6 caracteres.", true)
                false
            }
            else -> true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE4E6EB))
            .padding(top = 64.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Registrar Usuario",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF808D8E))
                )
                IconButton(
                    onClick = { navController?.navigate("loginScreen") },
                    modifier = Modifier
                        .background(color = Color(0xFF808D8E), shape = CircleShape)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Cerrar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    CustomTextField(
                        value = dni,
                        onValueChange = { dni = it },
                        label = "DNI",
                        focusRequester = dniFocusRequester,
                        nextFocusRequester = nombreFocusRequester,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = "Nombre",
                        focusRequester = nombreFocusRequester,
                        nextFocusRequester = apellidoPaternoFocusRequester,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = apellidoPaterno,
                        onValueChange = { apellidoPaterno = it },
                        label = "Apellido Paterno",
                        focusRequester = apellidoPaternoFocusRequester,
                        nextFocusRequester = apellidoMaternoFocusRequester,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = apellidoMaterno,
                        onValueChange = { apellidoMaterno = it },
                        label = "Apellido Materno",
                        focusRequester = apellidoMaternoFocusRequester,
                        nextFocusRequester = direccionFocusRequester,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = "Dirección",
                        focusRequester = direccionFocusRequester,
                        nextFocusRequester = celularFocusRequester,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = celular,
                        onValueChange = { celular = it },
                        label = "Celular",
                        focusRequester = celularFocusRequester,
                        nextFocusRequester = correoFocusRequester,
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = "Correo",
                        focusRequester = correoFocusRequester,
                        nextFocusRequester = claveFocusRequester,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    CustomTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = "Clave",
                        focusRequester = claveFocusRequester,
                        imeAction = ImeAction.Done,
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Button(
                        onClick = {
                            if (validarCampos()) {
                                isLoading = true

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
                                                        coroutineScope.launch {
                                                            try {
                                                                val request = RegistrarRequest(
                                                                    dni = dni,
                                                                    nombre = nombre,
                                                                    ap_paterno = apellidoPaterno,
                                                                    ap_materno = apellidoMaterno,
                                                                    direccion = direccion,
                                                                    celular = celular,
                                                                    correo = correo,
                                                                    estado = "A",
                                                                    activo = 1,
                                                                    password = clave
                                                                )
                                                                registrarService.registrarUser(request)
                                                                mostrarSnackbar("Usuario registrado exitosamente.", false)
                                                            } catch (e: Exception) {
                                                                mostrarSnackbar("Error al registrar usuario en la API: ${e.message}", false)
                                                            } finally {
                                                                isLoading = false
                                                            }
                                                        }
                                                    } else {
                                                        isLoading = false
                                                        mostrarSnackbar("Error al registrar usuario en la base de datos.", false)
                                                    }
                                                }

                                        } else {
                                            isLoading = false
                                            mostrarSnackbar("Error al registrar usuario: ${task.exception?.message}", false)
                                        }
                                    }
                            }
                        },
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .focusRequester(buttonFocusRequester)
                    ) {
                        Text("Registrar")
                    }
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color(0xFF808D8E)) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(onAny = {
            nextFocusRequester?.requestFocus()
        }),
        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        visualTransformation = visualTransformation,
        singleLine = true
    )
}

