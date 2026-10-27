package com.tumonedita.shared.features.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import com.tumonedita.shared.R
import com.tumonedita.shared.designsystem.*

data class Transaction(
    val id: String,
    val description: String,
    val amount: Double,
    val isIncome: Boolean,
    val date: String
)

@Composable
fun DashboardScreen(
    onNavigateToNewTransaction: () -> Unit
) {
    val transactions = listOf(
        Transaction("1", "Súper", -450.0, false, "12 abr. 2025"),
        Transaction("2", "Pago de nómina", 2000.0, true, "10 abr. 2025")
    )

    Scaffold(
        containerColor = BackgroundApp,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToNewTransaction,
                containerColor = GoldPrimary,
                contentColor = Color.White,
                modifier = Modifier.offset(y = 20.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Transacción")
            }
        }
    ) { padding ->
        // Aquí está el truco: NO usamos padding(padding) para que no baje todo.
        // En su lugar, usamos Box con fillMaxSize y el LazyColumn dentro.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp) // Solo margen lateral
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp)) // Espacio pequeño arriba

                    // Header: Hola, Ana + Icono de mano
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Hola, Ana", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Outlined.FrontHand,
                            contentDescription = "Saludo",
                            tint = GoldPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box {
                            Icon(Icons.Outlined.Notifications, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(24.dp))
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(RedExpense).align(Alignment.TopEnd))
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    // Tarjeta Dorada (Balance)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = GoldPrimary),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Tu saldo total", fontSize = 13.sp, color = Color.White.copy(alpha = 0.9f), fontWeight = FontWeight.Normal)
                                Icon(Icons.Outlined.Visibility, contentDescription = "Ver", tint = Color.White, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("$ 8,450.00", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Tarjetas de Ingresos y Gastos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Ingresos
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Canvas(modifier = Modifier.size(16.dp)) {
                                        val path = Path().apply {
                                            moveTo(size.width / 2, 0f)
                                            lineTo(size.width, size.height / 2)
                                            lineTo(size.width * 0.7f, size.height / 2)
                                            lineTo(size.width * 0.7f, size.height)
                                            lineTo(size.width * 0.3f, size.height)
                                            lineTo(size.width * 0.3f, size.height / 2)
                                            lineTo(0f, size.height / 2)
                                            close()
                                        }
                                        drawPath(path, color = GreenIncome)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Ingresos", fontSize = 12.sp, color = GreenIncome, fontWeight = FontWeight.Medium)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("$ 12,000.00", color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                            }
                        }
                        // Gastos
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Canvas(modifier = Modifier.size(16.dp)) {
                                        val path = Path().apply {
                                            moveTo(size.width / 2, size.height)
                                            lineTo(size.width, size.height / 2)
                                            lineTo(size.width * 0.7f, size.height / 2)
                                            lineTo(size.width * 0.7f, 0f)
                                            lineTo(size.width * 0.3f, 0f)
                                            lineTo(size.width * 0.3f, size.height / 2)
                                            lineTo(0f, size.height / 2)
                                            close()
                                        }
                                        drawPath(path, color = RedExpense)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Gastos", fontSize = 12.sp, color = RedExpense, fontWeight = FontWeight.Medium)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("$ 3,550.00", color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    // Gráfico Circular
                    Text("Gastos por categoría", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DonutChart(modifier = Modifier.size(100.dp))
                        Spacer(modifier = Modifier.width(24.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            LegendItem(color = ChartGold, name = "Comida", percent = "35%")
                            LegendItem(color = ChartGray, name = "Transporte", percent = "20%")
                            LegendItem(color = ChartPurple, name = "Vivienda", percent = "15%")
                            LegendItem(color = ChartBlue, name = "Entretenimiento", percent = "10%")
                            LegendItem(color = ChartLightGray, name = "Otros", percent = "20%")
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Últimos movimientos", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Lista de movimientos
                items(transactions) { transaction ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(if (transaction.isIncome) GreenIncomeBg else RedExpenseBg),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = if (transaction.isIncome) R.drawable.ic_salary else R.drawable.ic_super
                                        ),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            if (transaction.isIncome) GreenIncome else RedExpense
                                        ),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(transaction.description, color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(transaction.date, fontSize = 12.sp, color = TextSecondary)
                                }
                            }
                            Text(
                                text = if (transaction.isIncome) "+ $2,000.00" else "- $450.00",
                                color = if (transaction.isIncome) GreenIncome else RedExpense,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                // Espacio extra al final para que el botón flotante no tape el último elemento
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, name: String, percent: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(color))
            Spacer(modifier = Modifier.width(8.dp))
            Text(name, fontSize = 12.sp, color = TextPrimary, fontWeight = FontWeight.Normal)
        }
        Text(percent, fontSize = 12.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun DonutChart(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = size.minDimension * 0.25f
        drawArc(color = ChartGold, startAngle = -90f, sweepAngle = 126f, useCenter = false, style = Stroke(width = strokeWidth))
        drawArc(color = ChartGray, startAngle = 36f, sweepAngle = 72f, useCenter = false, style = Stroke(width = strokeWidth))
        drawArc(color = ChartPurple, startAngle = 108f, sweepAngle = 54f, useCenter = false, style = Stroke(width = strokeWidth))
        drawArc(color = ChartBlue, startAngle = 162f, sweepAngle = 36f, useCenter = false, style = Stroke(width = strokeWidth))
        drawArc(color = ChartLightGray, startAngle = 198f, sweepAngle = 72f, useCenter = false, style = Stroke(width = strokeWidth))
    }
}