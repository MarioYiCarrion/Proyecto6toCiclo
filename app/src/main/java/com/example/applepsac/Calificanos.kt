package com.example.applepsac

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.applepsac.auth.data.network.request.Comentario
import com.example.applepsac.core.retrofit.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CalificanosScreen() {
    var selectedStars by remember { mutableIntStateOf(0) }
    var selectedFace by remember { mutableIntStateOf(0) }
    var isSending by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val enviarCalificacion: () -> Unit = {
        coroutineScope.launch {
            if (selectedStars == 0 || selectedFace == 0) {
                snackbarHostState.showSnackbar("Por favor, califique ambas secciones")
                return@launch
            }

            isSending = true

            // Obtener fecha actual
            val fechaActual = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            // Calcular promedio
            val promedio = (selectedStars + selectedFace) / 2.0f

            val comentario = Comentario(
                descripcion = "Calificación enviada desde la app",
                fecha = fechaActual, // Enviar la fecha actual
                califica = promedio
            )

            RetrofitClient.instance.enviarComentario(comentario).enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                    isSending = false
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Gracias por su Calificación")
                        selectedStars = 0
                        selectedFace = 0
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    isSending = false
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Error al enviar la calificación")
                    }
                }
            })
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF0F0F0)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            RatingSection(
                title = "Facilidad",
                itemCount = 5,
                selectedItem = selectedStars,
                onItemSelected = { selectedStars = it },
                iconProvider = { Icons.Default.Star },
                activeColor = Color(0xFFBF360C),
                inactiveColor = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            RatingSection(
                title = "Experiencia",
                itemCount = 5,
                selectedItem = selectedFace,
                onItemSelected = { selectedFace = it },
                iconProvider = { i ->
                    ImageVector.vectorResource(id = when (i) {
                        1 -> R.drawable.baseline_sentiment_very_dissatisfied_24
                        2 -> R.drawable.baseline_sentiment_dissatisfied_24
                        3 -> R.drawable.baseline_sentiment_neutral_24
                        4 -> R.drawable.baseline_sentiment_satisfied_alt_24
                        5 -> R.drawable.baseline_sentiment_very_satisfied_24
                        else -> R.drawable.baseline_sentiment_neutral_24
                    })
                },
                activeColor = Color.Blue,
                inactiveColor = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            AnimatedButton(isSending = isSending, onClick = enviarCalificacion)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        SnackbarHost(hostState = snackbarHostState)
    }
}


@Composable
fun RatingSection(
    title: String,
    itemCount: Int,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    iconProvider: @Composable (Int) -> ImageVector,
    activeColor: Color,
    inactiveColor: Color
) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..itemCount) {
            Icon(
                imageVector = iconProvider(i),
                contentDescription = "$title $i",
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp)
                    .clickable { onItemSelected(i) }
                    .border(
                        width = 2.dp,
                        color = if (i == selectedItem) activeColor else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                tint = if (i <= selectedItem) activeColor else inactiveColor
            )
        }
    }
}

@Composable
fun AnimatedButton(
    isSending: Boolean,
    onClick: () -> Unit
) {
    val buttonColor by animateColorAsState(
        targetValue = if (isSending) Color.Gray else Color(0xFF0D6EFD),
        animationSpec = tween(durationMillis = 300),
        label = "Button Color Animation"
    )

    val buttonAlpha by animateFloatAsState(
        targetValue = if (isSending) 0.5f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "Button Alpha Animation"
    )

    val buttonText = if (isSending) "Enviando..." else "Enviar Calificación"

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(50.dp)
            .alpha(buttonAlpha)
            .animateContentSize()
    ) {
        if (isSending) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(text = buttonText, color = Color.White, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalificanosScreen() {
    CalificanosScreen()
}
