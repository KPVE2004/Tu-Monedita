package com.tumonedita.shared.features.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.tumonedita.shared.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tumonedita.shared.designsystem.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), // Mismo margen que el SVG
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono de la billetera con la moneda arriba
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(120.dp).width(100.dp)
        ) {
            // La billetera (abajo)
            Image(
                painter = painterResource(id = R.drawable.ic_wallet),
                contentDescription = "Billetera",
                modifier = Modifier.size(80.dp).align(Alignment.BottomCenter)
            )

            // La moneda (arriba, separada)
            Image(
                painter = painterResource(id = R.drawable.ic_coin),
                contentDescription = "Moneda",
                modifier = Modifier.size(70.dp).align(Alignment.TopCenter).offset(y = (15).dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tu monedita",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBlue // Azul oscuro exacto de tu SVG
        )
        Text(
            text = "Inicia sesión para continuar",
            fontSize = 14.sp,
            color = TextSecondary,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Campo de Correo
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Correo electrónico", fontSize = 14.sp, color = TextSecondary) },
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null, tint = TextSecondary) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = BorderColor,
                unfocusedContainerColor = SurfaceWhite,
                focusedContainerColor = SurfaceWhite
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Contraseña", fontSize = 14.sp, color = TextSecondary) },
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null, tint = TextSecondary) },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Mostrar contraseña",
                        tint = TextSecondary
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = BorderColor,
                unfocusedContainerColor = SurfaceWhite,
                focusedContainerColor = SurfaceWhite
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Iniciar Sesión
        Button(
            onClick = {
                viewModel.login(email) {
                    onNavigateToDashboard()
                }
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(50.dp), // Forma de píldora
            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color.White)
        ) {
            Text("Iniciar sesión", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Olvidaste tu contraseña
        TextButton(
            onClick = { /* Lógica futura */ }
        ) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = GoldPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divisor "o"
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = BorderColor)
            Text("o", modifier = Modifier.padding(horizontal = 16.dp), color = TextSecondary, fontSize = 12.sp)
            HorizontalDivider(modifier = Modifier.weight(1f), color = BorderColor)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Crear Cuenta
        OutlinedButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(50.dp), // Forma de píldora
            colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldPrimary),
            border = BorderStroke(1.5.dp, GoldPrimary)
        ) {
            Text("Crear cuenta", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}