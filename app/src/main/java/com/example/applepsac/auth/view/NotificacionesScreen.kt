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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applepsac.R
import com.example.applepsac.ui.theme.Poppins
import com.example.applepsac.ui.theme.ReemKufi


@Composable
fun Notificaciones(){
    Column() {
        MainToolbar()
        NotificationList()
    }

}

@Composable
fun NotificationList() {
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

            NotificationItem(
                icon = R.drawable.entregado,
                mainText = "Entregado",
                subText = "Pedido entregado exitosamente"
            )
            NotificationItem(
                icon = R.drawable.entransito,
                mainText = "En Transito",
                subText = "Su pedido ya se encuentra en Ruta"
            )
            NotificationItem(
                icon = R.drawable.procesandopedido,
                mainText = "Procesando Pedido",
                subText = "Se esta enpacando su pedido"
            )
            NotificationItem(
                icon = R.drawable.pedidoaceptado,
                mainText = "Pedido Aceptado",
                subText = "Su pedido ha sido aceptado "
            )
        }




    }
}

@Composable
fun NotificationItem(icon: Int, mainText: String, subText: String) {
    TextButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .height(40.dp)
//            .border(width = 1.dp, shape = RectangleShape, color = IconColor)
            ,
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
fun MainToolbar() {
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
            text = "Notificaciones",
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
