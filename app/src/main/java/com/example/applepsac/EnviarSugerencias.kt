package com.example.applepsac

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EnviarSugerencias() {
    val maxCharacters = 500
    val scrollState = rememberScrollState()
    val suggestionText = remember { mutableStateOf("") }
    val isSending = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val buttonColor by animateColorAsState(
        targetValue = if (isSending.value) Color.Gray else Color(0xFFB6CBE6),
        animationSpec = tween(durationMillis = 300),
        label = "Button Color Animation"
    )

    val buttonAlpha by animateFloatAsState(
        targetValue = if (isSending.value) 0.5f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "Button Alpha Animation"
    )

    val buttonText = if (isSending.value) "Enviado" else "Enviar"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE4E6EB))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.message),
                contentDescription = null,
                modifier = Modifier
                    .size(196.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
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
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFB6CBE6), shape = RoundedCornerShape(8.dp))
                    .focusRequester(focusRequester),
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
                                color = Color(0xFFE4E6EB),
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = !isSending.value,
                enter = fadeIn(animationSpec = tween(700)) + expandVertically(animationSpec = tween(700)),
                exit = fadeOut(animationSpec = tween(700)) + shrinkVertically(animationSpec = tween(700))
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isSending.value = true
                            delay(2000)
                            isSending.value = false
                            suggestionText.value = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    modifier = Modifier
                        .alpha(buttonAlpha)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = buttonText, color = Color.Black, fontSize = 18.sp)
                }
            }

            AnimatedVisibility(
                visible = isSending.value,
                enter = fadeIn(animationSpec = tween(700)) + expandVertically(animationSpec = tween(700)),
                exit = fadeOut(animationSpec = tween(700)) + shrinkVertically(animationSpec = tween(700))
            ) {
                CircularProgressIndicator(
                    color = Color(0xFF0D6EFD),
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEnviarSugerencias() {
    EnviarSugerencias()
}
