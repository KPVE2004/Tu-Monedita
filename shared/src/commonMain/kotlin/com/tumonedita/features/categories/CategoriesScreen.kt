package com.tumonedita.shared.features.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

data class Category(
    val id: String,
    val name: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)

@Composable
fun CategoriesScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    val expenseCategories = listOf(
        Category("1", "Comida y bebida", Icons.Outlined.Fastfood, Color(0xFFE57373)),
        Category("2", "Transporte", Icons.Outlined.DirectionsCar, Color(0xFFFFB74D)),
        Category("3", "Vivienda", Icons.Outlined.Home, Color(0xFF64B5F6)),
        Category("4", "Entretenimiento", Icons.Outlined.SportsEsports, Color(0xFFBA68C8)),
        Category("5", "Salud", Icons.Outlined.Favorite, Color(0xFFF06292)),
        Category("6", "Educación", Icons.Outlined.School, Color(0xFF4DB6AC)),
        Category("7", "Ropa", Icons.Outlined.Checkroom, Color(0xFFFFD54F)),
        Category("8", "Otros", Icons.Outlined.MoreHoriz, Color(0xFF90A4AE))
    )

    val incomeCategories = listOf(
        Category("9", "Salario", Icons.Outlined.Work, Color(0xFF81C784)),
        Category("10", "Inversiones", Icons.Outlined.TrendingUp, Color(0xFF4DB6AC))
    )

    val currentList = if (selectedTab == 0) expenseCategories else incomeCategories

    // Usamos un Column directamente, sin Scaffold ni TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
            .padding(horizontal = 20.dp)
    ) {
        // Espacio superior (para que no quede pegado al techo)
        Spacer(modifier = Modifier.height(24.dp))

        // Encabezado personalizado (Título azul + Botón agregar)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categorías",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue // <-- Título azul
            )
            IconButton(onClick = { /* Lógica futura */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar categoría",
                    tint = DarkBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Gastos / Ingresos (Tabs)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(SurfaceWhite),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tab Gastos
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (selectedTab == 0) GoldPrimary else Color.Transparent)
                    .clickable { selectedTab = 0 },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Gastos",
                    color = if (selectedTab == 0) Color.White else TextSecondary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            // Tab Ingresos
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (selectedTab == 1) GoldPrimary else Color.Transparent)
                    .clickable { selectedTab = 1 },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Ingresos",
                    color = if (selectedTab == 1) Color.White else TextSecondary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de Categorías
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(currentList) { category ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(category.color.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = category.icon,
                                    contentDescription = category.name,
                                    tint = category.color
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = category.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextPrimary
                            )
                        }

                        Icon(
                            imageVector = Icons.Outlined.ChevronRight,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                }
            }
            // Espacio extra al final para que la barra inferior no tape nada
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}