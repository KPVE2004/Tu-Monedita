package com.tumonedita.shared.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Colores exactos extraídos de tu SVG
val GoldPrimary = Color(0xFFC5992E)
val DarkBlue = Color(0xFF003882)
val BackgroundApp = Color(0xFFF8F8F5) // Fondo general claro
val SurfaceWhite = Color(0xFFFFFFFF) // Tarjetas
val TextPrimary = Color(0xFF1A1A1A) // Texto principal
val TextSecondary = Color(0xFF5B6470) // Texto secundario (fechas, etc.)
val BorderColor = Color(0xFFD8D8D4) // Bordes de las tarjetas

// Colores de estado (Ingresos/Gastos)
val GreenIncome = Color(0xFF0F7A4F)
val GreenIncomeBg = Color(0xFFD8F3E7)
val RedExpense = Color(0xFFC0392B)
val RedExpenseBg = Color(0xFFFDE1DD)

// Colores del gráfico
val ChartGold = Color(0xFFC5992E)
val ChartGray = Color(0xFF9AA0A6)
val ChartPurple = Color(0xFF8A63D2)
val ChartBlue = Color(0xFF003882)
val ChartLightGray = Color(0xFFD1D1D1)

// Tipografía por defecto (puedes cambiarla a Poppins si descargas la fuente)
val AppTypography = androidx.compose.material3.Typography(
    headlineLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 22.sp, color = TextPrimary),
    titleMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = TextPrimary),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp, color = TextSecondary),
)

private val LightColorScheme = lightColorScheme(
    primary = GoldPrimary,
    onPrimary = Color.White,
    background = BackgroundApp,
    surface = SurfaceWhite,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = RedExpense
)

@Composable
fun TuMoneditaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}