package com.tumonedita.shared.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tumonedita.shared.designsystem.*
import com.tumonedita.shared.features.budgets.BudgetsScreen
import com.tumonedita.shared.features.categories.CategoriesScreen
import com.tumonedita.shared.features.dashboard.DashboardScreen
import com.tumonedita.shared.features.profile.ProfileScreen
import com.tumonedita.shared.features.reports.ReportsScreen

@Composable
fun MainScreen(
    onLogout: () -> Unit,
    onNavigateToNewTransaction: () -> Unit // <-- NUEVO PARÁMETRO
) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = BackgroundApp,
        bottomBar = {
            Surface(
                color = SurfaceWhite,
                shadowElevation = 12.dp,
                modifier = Modifier.fillMaxWidth().height(70.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val tabs = listOf(
                        "Inicio" to Icons.Outlined.Home,
                        "Categorías" to Icons.Outlined.PieChart,
                        "Presupuestos" to Icons.Outlined.DateRange,
                        "Reportes" to Icons.Outlined.BarChart,
                        "Perfil" to Icons.Outlined.Person
                    )

                    tabs.forEachIndexed { index, (title, icon) ->
                        val isSelected = selectedTab == index
                        val color = if (isSelected) GoldPrimary else TextSecondary

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                                .clickable { selectedTab = index }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = title,
                                tint = color,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = title,
                                fontSize = 10.sp,
                                color = color,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> DashboardScreen(onNavigateToNewTransaction = onNavigateToNewTransaction) // <-- LE PASAMOS LA FUNCIÓN
                1 -> CategoriesScreen()
                2 -> BudgetsScreen()
                3 -> ReportsScreen()
                4 -> ProfileScreen(onLogout = onLogout)
            }
        }
    }
}