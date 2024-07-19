package com.example.applepsac

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EnviarSugerencias(navController: NavHostController? = null) {
    val maxCharacters = 500
    val scrollState = rememberScrollState()
    val suggestionText = remember { mutableStateOf("") }
    val isSending = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() } // Añadido para manejar el foco

    LaunchedEffect(Unit) {
        focusRequester.requestFocus() // Posicionar el cursor en el BasicTextField
    }

    val buttonColor by animateColorAsState(
        targetValue = if (isSending.value) Color.Gray else Color(0xFF6200EE),
        animationSpec = tween(durationMillis = 300)
    )

    val buttonAlpha by animateFloatAsState(
        targetValue = if (isSending.value) 0.5f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    val buttonText = if (isSending.value) "Enviado" else "Enviar"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // Fondo sutil más claro
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enviar Sugerencias",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = suggestionText.value,
                onValueChange = {
                    if (it.length <= maxCharacters) {
                        suggestionText.value = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(8.dp)) // Borde oscuro
                    .focusRequester(focusRequester), // Añadido para manejar el foco
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Hide keyboard */ }
                ),
                maxLines = 10,
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (suggestionText.value.isEmpty()) {
                            Text(
                                text = "Escribe tu sugerencia aquí...",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        isSending.value = true
                        delay(2000) // Simulate network request
                        isSending.value = false
                        suggestionText.value = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                modifier = Modifier
                    .alpha(buttonAlpha)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = buttonText, color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEnviarSugerencias() {
    EnviarSugerencias()
}
