package com.tumonedita.shared.features.authentication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tumonedita.shared.R
import com.tumonedita.shared.designsystem.*

data class OnboardingPage(
    val titleLine1: String,
    val titleLine2: String,
    val description: String,
    val iconType: Int
)

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            titleLine1 = "Registra tus",
            titleLine2 = "ingresos y gastos",
            description = "Lleva un control de tu dinero de forma fácil y rápida.",
            iconType = 0
        ),
        OnboardingPage(
            titleLine1 = "Organiza tus gastos",
            titleLine2 = "por categorías",
            description = "Descubre en qué gastas más y toma mejores decisiones.",
            iconType = 1
        ),
        OnboardingPage(
            titleLine1 = "Cumple tus",
            titleLine2 = "objetivos",
            description = "Crea presupuestos y da seguimiento a tus metas financieras.",
            iconType = 2
        )
    )

    var currentPage by remember { mutableStateOf(0) }
    val page = pages[currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Espacio más pequeño para subir el contenido
        Spacer(modifier = Modifier.height(65.dp))

        // Círculo con el icono adentro
        Box(
            modifier = Modifier
                .size(280.dp) // Un poco más pequeño
                .clip(CircleShape)
                .background(Color(0xFFF0F2F7)),
            contentAlignment = Alignment.Center
        ) {
            // Si es la pantalla 3, usamos la imagen, si no, el Canvas
            if (page.iconType == 2) {
                Image(
                    painter = painterResource(id = R.drawable.ic_onboarding_target),
                    contentDescription = "Objetivo",
                    modifier = Modifier.size(180.dp) // Ajusta el tamaño del SVG si es necesario
                )
            } else {
                OnboardingIcon(type = page.iconType, modifier = Modifier.size(180.dp)) // Iconos más pequeños
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Textos
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = page.titleLine1,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue,
                textAlign = TextAlign.Center
            )
            Text(
                text = page.titleLine2,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = page.description,
                fontSize = 18.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 26.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Puntitos
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pages.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (index == currentPage) GoldPrimary else Color(0xFFD1D1D1))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onFinish) {
                Text("Saltar", color = GoldPrimary, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            }
            Button(
                onClick = { if (currentPage < pages.size - 1) currentPage++ else onFinish() },
                modifier = Modifier.height(56.dp).width(170.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color.White)
            ) {
                Text(
                    text = if (currentPage == pages.size - 1) "Comenzar" else "Siguiente",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun OnboardingIcon(type: Int, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2, size.height / 2)

        when (type) {
            0 -> {
                // Billetera y Moneda (Moneda sólida y un poco más arriba)
                // Moneda principal
                drawCircle(color = GoldPrimary, radius = size.width * 0.18f, center = Offset(center.x, size.height * 0.20f)) // Subida y más pequeña
                // Moneda pequeña flotante
                drawCircle(color = Color(0xFFE3B94F), radius = size.width * 0.07f, center = Offset(size.width * 0.82f, size.height * 0.38f))
                // Puntito a la izquierda
                drawCircle(color = Color(0xFFE3B94F), radius = size.width * 0.03f, center = Offset(size.width * 0.15f, size.height * 0.35f))

                // Billetera
                drawRoundRect(
                    color = DarkBlue,
                    topLeft = Offset(size.width * 0.1f, size.height * 0.48f), // Un poco más abajo para dar espacio
                    size = Size(size.width * 0.8f, size.height * 0.4f),
                    cornerRadius = CornerRadius(24f)
                )
                // Tapa de la billetera
                drawRoundRect(
                    color = DarkBlue,
                    topLeft = Offset(size.width * 0.1f, size.height * 0.43f),
                    size = Size(size.width * 0.8f, size.height * 0.1f),
                    cornerRadius = CornerRadius(12f)
                )
                // Broche blanco
                drawRoundRect(
                    color = Color.White,
                    topLeft = Offset(size.width * 0.65f, size.height * 0.58f),
                    size = Size(size.width * 0.25f, size.height * 0.12f),
                    cornerRadius = CornerRadius(16f)
                )
                // Punto dorado del broche
                drawCircle(color = GoldPrimary, radius = size.width * 0.03f, center = Offset(size.width * 0.75f, size.height * 0.64f))
            }
            1 -> {
                // Gráfico
                drawRoundRect(color = DarkBlue, topLeft = Offset(size.width * 0.1f, size.height * 0.65f), size = Size(size.width * 0.18f, size.height * 0.25f), cornerRadius = CornerRadius(6f))
                drawRoundRect(color = GoldPrimary, topLeft = Offset(size.width * 0.32f, size.height * 0.5f), size = Size(size.width * 0.18f, size.height * 0.4f), cornerRadius = CornerRadius(6f))
                drawRoundRect(color = Color.LightGray, topLeft = Offset(size.width * 0.54f, size.height * 0.4f), size = Size(size.width * 0.18f, size.height * 0.5f), cornerRadius = CornerRadius(6f))

                // Pastel
                drawArc(color = DarkBlue, startAngle = 0f, sweepAngle = 180f, useCenter = true, topLeft = Offset(size.width * 0.6f, size.height * 0.1f), size = Size(size.width * 0.4f, size.height * 0.4f))
                drawArc(color = GoldPrimary, startAngle = 180f, sweepAngle = 90f, useCenter = true, topLeft = Offset(size.width * 0.6f, size.height * 0.1f), size = Size(size.width * 0.4f, size.height * 0.4f))
                drawArc(color = Color(0xFF8FB0E8), startAngle = 270f, sweepAngle = 90f, useCenter = true, topLeft = Offset(size.width * 0.6f, size.height * 0.1f), size = Size(size.width * 0.4f, size.height * 0.4f))
            }
            // El tipo 2 (Diana) ya no se usa aquí, porque usamos la Image del SVG arriba.
        }
    }
}