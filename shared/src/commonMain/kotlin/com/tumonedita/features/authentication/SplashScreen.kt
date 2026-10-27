package com.tumonedita.shared.features.authentication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tumonedita.shared.R
import com.tumonedita.shared.designsystem.*

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Dibujamos las formas de fondo (Arco azul arriba, Arco dorado abajo)
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Arco Azul (Arriba a la derecha)
            val bluePath = Path().apply {
                moveTo(size.width, 0f)
                lineTo(size.width, size.height * 0.22f)
                cubicTo(
                    size.width * 0.8f, size.height * 0.20f,
                    size.width * 0.6f, size.height * 0.10f,
                    size.width * 0.6f, 0f
                )
                close()
            }
            drawPath(bluePath, color = DarkBlue)

            // Arco Dorado (Abajo)
            val goldPath = Path().apply {
                moveTo(0f, size.height * 0.65f)
                cubicTo(
                    size.width * 0.2f, size.height * 0.72f,
                    size.width * 0.3f, size.height * 0.85f,
                    size.width * 0.6f, size.height * 0.85f
                )
                cubicTo(
                    size.width * 0.8f, size.height * 0.85f,
                    size.width * 0.9f, size.height * 0.80f,
                    size.width, size.height * 0.75f
                )
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            drawPath(goldPath, color = GoldPrimary)
        }

        // Contenido central (Logo y Textos)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-40).dp)
        ) {
            // Logo
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.height(140.dp).width(120.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_wallet),
                    contentDescription = "Billetera",
                    modifier = Modifier.size(100.dp).align(Alignment.BottomCenter)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_coin),
                    contentDescription = "Moneda",
                    modifier = Modifier.size(90.dp).align(Alignment.TopCenter).offset(y = 15.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Texto "Tu monedita" con dos colores
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = DarkBlue, fontWeight = FontWeight.Bold)) {
                        append("Tu ")
                    }
                    withStyle(style = SpanStyle(color = GoldPrimary, fontWeight = FontWeight.Bold)) {
                        append("monedita")
                    }
                },
                fontSize = 38.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tu dinero, en tus manos",
                fontSize = 19.sp,
                color = TextSecondary
            )
        }
    }
}