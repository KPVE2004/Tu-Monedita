package com.tumonedita.shared.features.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Formas de fondo
        Canvas(modifier = Modifier.fillMaxSize()) {
            val bluePath = Path().apply {
                moveTo(size.width, 0f)
                lineTo(size.width, size.height * 0.2f)
                cubicTo(
                    size.width * 0.8f, size.height * 0.18f,
                    size.width * 0.6f, size.height * 0.08f,
                    size.width * 0.6f, 0f
                )
                close()
            }
            drawPath(bluePath, color = DarkBlue)

            val goldPath = Path().apply {
                moveTo(0f, size.height * 0.7f)
                cubicTo(
                    size.width * 0.2f, size.height * 0.75f,
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

        // Contenido
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(80.dp))

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

            // Título
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = DarkBlue, fontWeight = FontWeight.Bold)) { append("Tu ") }
                    withStyle(style = SpanStyle(color = GoldPrimary, fontWeight = FontWeight.Bold)) { append("monedita") }
                },
                fontSize = 38.sp
            )
            Text(
                text = "Tu dinero, en tus manos",
                fontSize = 19.sp,
                color = TextSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botones (Subidos y Azules)
            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 250.dp), // Subidos más
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onNavigateToLogin,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkBlue, contentColor = Color.White) // Azul
                ) {
                    Text("Iniciar sesión", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onNavigateToRegister,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkBlue), // Azul
                    border = BorderStroke(1.5.dp, DarkBlue) // Borde azul
                ) {
                    Text("Crear cuenta", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}