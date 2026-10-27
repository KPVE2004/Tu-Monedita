package com.tumonedita.shared.features.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tumonedita.shared.R
import com.tumonedita.shared.designsystem.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono de la billetera con la moneda arriba
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(120.dp).width(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_wallet),
                contentDescription = "Billetera",
                modifier = Modifier.size(80.dp).align(Alignment.BottomCenter)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_coin),
                contentDescription = "Moneda",
                modifier = Modifier.size(70.dp).align(Alignment.TopCenter).offset(y = 17.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tu monedita",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBlue
        )

        // Texto "Crea tu cuenta" más grande
        Text(
            text = "Crea tu cuenta",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Nombre
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Nombre completo", fontSize = 14.sp, color = TextSecondary) },
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null, tint = TextSecondary) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = BorderColor,
                unfocusedContainerColor = SurfaceWhite,
                focusedContainerColor = SurfaceWhite
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo Correo
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

        // Campo Contraseña
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

        // Botón Registrarse
        Button(
            onClick = {
                viewModel.register(email) {
                    onRegisterSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color.White)
        ) {
            Text("Registrarse", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de Términos con estilos (Gris y Dorado)
        val termsText = buildAnnotatedString {
            append("Al registrarte aceptas nuestros\n")
            withStyle(style = SpanStyle(color = GoldPrimary, fontWeight = FontWeight.Bold)) {
                append("Términos y Condiciones")
            }
            append(" y ")
            withStyle(style = SpanStyle(color = GoldPrimary, fontWeight = FontWeight.Bold)) {
                append("Política de Privacidad")
            }
        }

        Text(
            text = termsText,
            fontSize = 10.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}