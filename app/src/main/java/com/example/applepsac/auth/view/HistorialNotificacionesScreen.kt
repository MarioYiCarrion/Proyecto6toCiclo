package com.example.applepsac.auth.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applepsac.R
import com.example.applepsac.auth.data.network.request.HistorialNotificacion
import com.example.applepsac.auth.viewmodel.HistorialNotificacionesViewModel
import com.example.applepsac.ui.theme.Poppins
import com.example.applepsac.ui.theme.ReemKufi
@Composable
fun HistorialNotificaciones(viewModel: HistorialNotificacionesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    // Cargar el historial de notificaciones cuando se monta la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarHistorialNotificaciones(userId = 1) // Reemplaza con el ID de usuario correspondiente
    }

    Column {
        MainToolbar1()
        NotificationList1(notificaciones = viewModel.historialNotificaciones)
    }
}

@Composable
fun NotificationList1(notificaciones: List<HistorialNotificacion>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(bottom = 60.dp)
    ) {
        item {
            Text(
                text = "Hoy",
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            notificaciones.forEach { notificacion ->
                NotificationItem1(
                    icon = R.drawable.notificacion, // Usa un ícono genérico o específico
                    mainText = notificacion.descripcion,
                    subText = "Estado: ${notificacion.estado}" // O cualquier otro campo relevante
                )
            }
        }
    }
}

@Composable
fun NotificationItem1(icon: Int, mainText: String, subText: String) {
    TextButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = icon), contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = mainText,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        lineHeight = 10.sp,
                        fontSize = 12.sp,
                        letterSpacing = 0.sp
                    )
                    Text(
                        text = subText,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        lineHeight = 10.sp,
                        fontSize = 11.sp,
                        letterSpacing = 0.sp,
                        modifier = Modifier
                            .offset(y = (-4).dp)
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun MainToolbar1() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(horizontal = 10.dp, vertical = 30.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    )
    {
//        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "Historial de Notificaciones",
            color = Color.Black,
            fontFamily = ReemKufi,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
//        IconButton(onClick = {}) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_close), contentDescription = "",
//                tint = IconColor,
//                modifier = Modifier.size(28.dp)
//            )
//        }
    }

}
