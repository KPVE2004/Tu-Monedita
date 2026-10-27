package com.tumonedita.shared.features.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tumonedita.shared.designsystem.*

@Composable
fun ReportsScreen() {
    var isExpense by remember { mutableStateOf(true) }

    val expenseCategories = listOf(
        Triple("Comida", "35%", Color(0xFFE57373)),
        Triple("Transporte", "20%", Color(0xFFFFB74D)),
        Triple("Vivienda", "15%", Color(0xFF64B5F6)),
        Triple("Entretenimiento", "10%", Color(0xFFBA68C8)),
        Triple("Otros", "20%", Color(0xFF90A4AE))
    )
    val incomeCategories = listOf(
        Triple("Salario", "80%", Color(0xFF81C784)),
        Triple("Inversiones", "15%", Color(0xFF4DB6AC)),
        Triple("Otros", "5%", Color(0xFF90A4AE))
    )
    val currentList = if (isExpense) expenseCategories else incomeCategories

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Reportes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selector Gastos / Ingresos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(SurfaceWhite),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (isExpense) GoldPrimary else Color.Transparent)
                    .clickable { isExpense = true },
                contentAlignment = Alignment.Center
            ) {
                Text("Gastos", color = if (isExpense) Color.White else TextSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (!isExpense) GoldPrimary else Color.Transparent)
                    .clickable { isExpense = false },
                contentAlignment = Alignment.Center
            ) {
                Text("Ingresos", color = if (!isExpense) Color.White else TextSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Selector de Fecha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Lógica futura */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Anterior", tint = TextSecondary)
            }
            Text(
                text = "Abril 2025",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            IconButton(onClick = { /* Lógica futura */ }) {
                Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = "Siguiente", tint = TextSecondary)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gráfico de Barras (Corregido)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Resumen del mes por semanas", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp), // Altura suficiente para las barras y las etiquetas
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val labels = listOf("S1", "S2", "S3", "S4")

                    labels.forEachIndexed { index, week ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            val categoryColors = if (isExpense) {
                                listOf(Color(0xFFE57373), Color(0xFFFFB74D), Color(0xFF64B5F6))
                            } else {
                                listOf(Color(0xFF81C784), Color(0xFF4DB6AC), Color(0xFF90A4AE))
                            }

                            // Espacio flexible para empujar las barras hacia abajo
                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height((40 + index * 10).dp)
                                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                    .background(categoryColors[0])
                            )
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height((20 + index * 5).dp)
                                    .background(categoryColors[1])
                            )
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height((15 + index * 3).dp)
                                    .clip(RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp))
                                    .background(categoryColors[2])
                            )

                            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre barra y texto

                            Text(
                                text = week,
                                fontSize = 11.sp,
                                color = TextSecondary,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp) // Espacio extra abajo
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(if (isExpense) "Gastos por categoría" else "Ingresos por categoría", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(currentList.size) { index ->
                val (name, percent, color) = currentList[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceWhite)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(color))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(name, fontSize = 14.sp, color = TextPrimary, modifier = Modifier.weight(1f))
                    Text(percent, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}