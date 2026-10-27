package com.tumonedita.shared.features.budgets

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tumonedita.shared.designsystem.*

data class BudgetCategory(
    val name: String,
    val spent: Double,
    val total: Double,
    val color: Color,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun BudgetsScreen() {
    val categories = listOf(
        BudgetCategory("Comida", 640.0, 1000.0, Color(0xFFE57373), Icons.Outlined.Fastfood),
        BudgetCategory("Transporte", 300.0, 800.0, Color(0xFFFFB74D), Icons.Outlined.DirectionsCar),
        BudgetCategory("Entretenimiento", 250.0, 600.0, Color(0xFFBA68C8), Icons.Outlined.SportsEsports)
    )
    val totalSpent = categories.sumOf { it.spent }
    val totalBudget = categories.sumOf { it.total }
    val totalRemaining = totalBudget - totalSpent
    val totalProgress = (totalSpent / totalBudget).toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Encabezado (Título + Botón Agregar)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Presupuestos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue
            )
            IconButton(onClick = { /* Lógica futura */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar presupuesto",
                    tint = DarkBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tarjeta del Total del Mes
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total del mes", fontSize = 12.sp, color = TextSecondary)
                Text("$ ${totalBudget}", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = totalProgress,
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = GoldPrimary,
                    trackColor = Color(0xFFE0E0E0)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$ ${totalRemaining} restantes", fontSize = 12.sp, color = TextSecondary)
                    Text("${(totalProgress * 100).toInt()}%", fontSize = 12.sp, color = GoldPrimary, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Detalle por categoría", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(modifier = Modifier.height(12.dp))

        // Lista de categorías
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier.size(32.dp).clip(CircleShape).background(category.color.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(category.icon, contentDescription = null, tint = category.color, modifier = Modifier.size(18.dp))
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(category.name, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                            }
                            Text("$ ${category.total}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        LinearProgressIndicator(
                            progress = (category.spent / category.total).toFloat(),
                            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                            color = category.color,
                            trackColor = Color(0xFFE0E0E0)
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("$ ${category.total - category.spent} restantes", fontSize = 11.sp, color = TextSecondary)
                            Text("${((category.spent / category.total) * 100).toInt()}%", fontSize = 11.sp, color = category.color, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}