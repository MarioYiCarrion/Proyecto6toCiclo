package com.example.applepsac

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.SolidColor
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun CalificanosScreen(navController: NavHostController? = null) {
    var selectedStars by remember { mutableStateOf(0) }
    var selectedFace by remember { mutableStateOf(0) }
    var showThankYou by remember { mutableStateOf(false) }

    if (showThankYou) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Gracias!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            LaunchedEffect(Unit) {
                delay(1500)
                showThankYou = false
            }
        }
    } else {
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
                Text(
                    text = "Facilidad",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star $i",
                            modifier = Modifier
                                .size(64.dp) // Tamaño de las estrellas
                                .padding(4.dp)
                                .clickable { selectedStars = i }
                                .border(
                                    width = 2.dp,
                                    color = if (i <= selectedStars) Color.Black else Color.Transparent,
                                    shape = MaterialTheme.shapes.small
                                ),
                            tint = if (i <= selectedStars) Color.Yellow else Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Experiencia",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = when (i) {
                                1 -> R.drawable.baseline_sentiment_very_dissatisfied_24
                                2 -> R.drawable.baseline_sentiment_dissatisfied_24
                                3 -> R.drawable.baseline_sentiment_neutral_24
                                4 -> R.drawable.baseline_sentiment_satisfied_alt_24
                                5 -> R.drawable.baseline_sentiment_very_satisfied_24
                                else -> R.drawable.baseline_sentiment_neutral_24
                            }),
                            contentDescription = "Face $i",
                            modifier = Modifier
                                .size(64.dp) // Tamaño de las caritas
                                .padding(4.dp)
                                .clickable { selectedFace = i },
                            tint = if (i == selectedFace) Color.Blue else Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        selectedStars = 0
                        selectedFace = 0
                        showThankYou = true
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .width(IntrinsicSize.Max) // Ajusta el ancho del botón
                ) {
                    Text(text = "Enviar Calificación")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCalificanosScreen() {
//    CalificanosScreen()
//}
