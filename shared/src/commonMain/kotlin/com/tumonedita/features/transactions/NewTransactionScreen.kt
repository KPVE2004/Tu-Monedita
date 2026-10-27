package com.tumonedita.shared.features.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import com.tumonedita.shared.designsystem.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionScreen(
    onNavigateBack: () -> Unit,
    onSaveTransaction: () -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = BackgroundApp,
        bottomBar = {
            // Botón de Guardar fijo al fondo
            Surface(
                color = BackgroundApp,
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Button(
                    onClick = {
                        if (amount.isNotBlank()) {
                            onSaveTransaction()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color.White)
                ) {
                    Text("Guardar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Encabezado (Flecha atrás + Título)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Nueva transacción",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Selector Gasto / Ingreso
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
                    Text("Gasto", color = if (isExpense) Color.White else TextSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                    Text("Ingreso", color = if (!isExpense) Color.White else TextSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Selector de Categoría (simulado)
            Card(
                modifier = Modifier.fillMaxWidth().height(64.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icono circular rosa
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFE8567F)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Outlined.ShoppingBag, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Categoría", fontSize = 12.sp, color = TextSecondary)
                        Text("Selecciona una categoría", fontSize = 14.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Monto
            Card(
                modifier = Modifier.fillMaxWidth().height(64.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.AttachMoney, contentDescription = null, tint = DarkBlue)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Monto", fontSize = 12.sp, color = TextSecondary)
                        BasicTextField(
                            value = amount,
                            onValueChange = { amount = it },
                            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, color = TextPrimary, fontWeight = FontWeight.Medium),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            decorationBox = { innerTextField ->
                                if (amount.isEmpty()) {
                                    Text("0.00", fontSize = 14.sp, color = TextSecondary)
                                }
                                innerTextField()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Fecha (simulado)
            Card(
                modifier = Modifier.fillMaxWidth().height(64.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.DateRange, contentDescription = null, tint = DarkBlue)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Fecha", fontSize = 12.sp, color = TextSecondary)
                        Text("12 abr. 2025", fontSize = 14.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Descripción
            Card(
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
                    Text("Descripción (opcional)", fontSize = 12.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    BasicTextField(
                        value = description,
                        onValueChange = { description = it },
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, color = TextPrimary),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (description.isEmpty()) {
                                Text("Ej. Supermercado", fontSize = 14.sp, color = TextSecondary)
                            }
                            innerTextField()
                        }
                    )
                }
            }
        }
    }
}