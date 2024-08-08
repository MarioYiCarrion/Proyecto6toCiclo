package com.example.applepsac.auth.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applepsac.R
import com.example.applepsac.ui.theme.Poppins
import com.example.applepsac.ui.theme.ReemKufi
import com.uistack.jetpackcomposedemo3.ui.theme.Shapes


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FAQScreen(){

    Column {
        FAQToolbar()
        FAQSection()
        FAQToolbar2()
        FAQSection2()
    }
    BottomText()

}

@Composable
fun BottomText() {
    Box (
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.padding(20.dp)
    ){
        Text(text = "¿No encuentras una respuesta a tus preguntas? No dude en contactarnos en  info@lepsac.net.pe",
            fontSize = 12.sp, color = Color.Gray,
            fontFamily = Poppins
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun FAQSection() {
    Column() {
        ExpandableFAQCard(
            "¿Qué ventajas tiene esta aplicación?", "La aplicación Lepsac, además del nuevo diseño visual que tiene, es internamente más segura, ya que la información de formularios, sesiones y registros se implementa con POST. Esto evita que la información del dispositivo quede expuesta."
        )
        ExpandableFAQCard(
            "¿Si ya tengo instalada la aplicación anterior, necesito desinstalarla e instalar la nueva?", "Si la versión instalada de la aplicación Lepsac es 3.6.2, solo necesita actualizar desde Google Store. Si tienes una versión inferior a la mencionada, tendrás que desinstalar e instalar la nueva versión 4.0"
        )
        ExpandableFAQCard(
            "¿Qué puedo personalizar en la nueva versión de la aplicación Lepsac?",
            "Puedes personalizar la aplicación en diseño y funcionalidad; deberá consultar a su administrador de cuentas."
        )
        ExpandableFAQCard(
            "¿Cómo puedo obtener la nueva versión de la aplicación?",
            "Si ya cuentas con la aplicación Lepsac personalizada con tu logo, colores institucionales y marca, deberás comunicarte con tu Account Manager para enviar tu solicitud de actualización a nuestra área de Software Factory y tu aplicación podrá ser actualizada."
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun FAQSection2() {
    Column() {
        ExpandableFAQCard(
            "Cómo Configurar un Nuevo Dispositivo Android", "Enciende el dispositivo.\n" +
                    "Selecciona el idioma y presiona \"Empezar\".\n" +
                    "Conéctate a una red Wi-Fi.\n" +
                    "Inicia sesión con tu cuenta de Google o crea una nueva.\n" +
                    "Sigue las instrucciones para configurar opciones de seguridad, como el bloqueo de pantalla.\n" +
                    "Restaura tus aplicaciones y datos de una copia de seguridad (si tienes una).\n" +
                    "Configura los servicios de Google según tus preferencias.\n" +
                    "Personaliza tu dispositivo, instala aplicaciones y ajusta configuraciones adicionales según tus necesidades."
        )
        ExpandableFAQCard(
            "Cómo Liberar Espacio en un Teléfono Android", "Elimina aplicaciones no utilizadas desde Configuración > Aplicaciones.\n" +
                    "Borra archivos y datos almacenados en caché desde Configuración > Almacenamiento > Caché de datos.\n" +
                    "Usa aplicaciones de limpieza como Google Files para encontrar y eliminar archivos innecesarios.\n" +
                    "Transfiere fotos y videos a un servicio de almacenamiento en la nube como Google Photos.\n" +
                    "Borra mensajes de texto antiguos y conversaciones de aplicaciones de mensajería.\n" +
                    "Revisa y elimina descargas desde la carpeta \"Descargas\".\n" +
                    "Desinstala actualizaciones de aplicaciones del sistema que no necesitas."
        )
        ExpandableFAQCard(
            "Cómo Actualizar el Sistema Operativo en un Dispositivo Android",
            "Ve a Configuración > Sistema > Actualización del sistema.\n" +
                    "Presiona \"Buscar actualizaciones\".\n" +
                    "Si hay una actualización disponible, selecciona \"Descargar e instalar\".\n" +
                    "Sigue las instrucciones en pantalla para completar la actualización.\n" +
                    "Tu dispositivo se reiniciará y aplicará la actualización."
        )

    }
}

@ExperimentalMaterialApi
@Composable
fun ExpandableFAQCard(title: String, description: String) {
    var expandedState by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.small,
        onClick = {
            expandedState = !expandedState
        },
        backgroundColor = Color.White
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    modifier = Modifier.weight(6f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines =if(!expandedState) 1 else 3,
                    overflow =if(!expandedState) TextOverflow.Ellipsis else TextOverflow.Visible,
                    fontFamily = Poppins
                )
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier
                        .weight(1f)
                        .alpha(.5f),
                ) {
                    if (expandedState)
                        Icon(
                            painterResource(id = R.drawable.splash), contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )
                    else
                        Icon(
                            imageVector = Icons.Default.Add, contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )


                }
            }

            if (expandedState) {
                Text(
                    text = description,
                    modifier = Modifier.padding(bottom = 40.dp),
                    fontSize = 14.sp,
                    color = Color.Black,
                    lineHeight = 18.sp
                )
            }

        }

    }
}

@Composable
fun FAQToolbar(){
    androidx.compose.material.Text(
        text = "Preguntas Frecuentes",
        color = Color.Black,
        fontFamily = ReemKufi,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(horizontal = 10.dp, vertical = 30.dp),
        textAlign = TextAlign.Center
    )

}

@Composable
fun FAQToolbar2(){
    Text(
        text = "Guias de Uso",
        color = Color.Black,
        fontFamily = ReemKufi,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(horizontal = 10.dp, vertical = 30.dp),
        textAlign = TextAlign.Center
    )

}