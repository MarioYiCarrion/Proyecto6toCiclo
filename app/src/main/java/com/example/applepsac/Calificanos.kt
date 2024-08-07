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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CalificanosScreen() {
    var selectedStars by remember { mutableIntStateOf(0) }
    var selectedFace by remember { mutableIntStateOf(0) }
    var isSending by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RatingSection(
                title = "Facilidad",
                itemCount = 5,
                selectedItem = selectedStars,
                onItemSelected = { selectedStars = it },
                iconProvider = { Icons.Default.Star },
                activeColor = Color(0xFFBF360C), // Naranja oscuro
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

            AnimatedButton(
                isSending = isSending,
                onClick = {
                    coroutineScope.launch {
                        isSending = true
                        delay(2000)
                        isSending = false
                        snackbarHostState.showSnackbar("Gracias por su Calificación")
                        selectedStars = 0
                        selectedFace = 0
                    }
                }
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
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
