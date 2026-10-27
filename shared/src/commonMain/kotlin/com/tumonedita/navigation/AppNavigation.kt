package com.tumonedita.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tumonedita.shared.features.MainScreen
import com.tumonedita.shared.features.authentication.AuthViewModel
import com.tumonedita.shared.features.authentication.LoginScreen
import com.tumonedita.shared.features.authentication.OnboardingScreen
import com.tumonedita.shared.features.authentication.RegisterScreen
import com.tumonedita.shared.features.authentication.SplashScreen
import com.tumonedita.shared.features.authentication.WelcomeScreen
import com.tumonedita.shared.features.transactions.NewTransactionScreen // <-- Import
import kotlinx.coroutines.delay

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    val startDestination = SplashRoute

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ... (Splash, Onboarding, Welcome, Login, Register ...

        composable<SplashRoute> {
            SplashScreen()
            LaunchedEffect(Unit) {
                delay(5000)
                navController.navigate(OnboardingRoute) {
                    popUpTo(SplashRoute) { inclusive = true }
                }
            }
        }

        composable<OnboardingRoute> {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(OnboardingRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<WelcomeRoute> {
            WelcomeScreen(
                onNavigateToLogin = { navController.navigate(LoginRoute) },
                onNavigateToRegister = { navController.navigate(RegisterRoute) }
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRegister = { navController.navigate(RegisterRoute) },
                onNavigateToDashboard = {
                    navController.navigate(DashboardRoute) {
                        popUpTo(WelcomeRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<RegisterRoute> {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(DashboardRoute) {
                        popUpTo(WelcomeRoute) { inclusive = true }
                    }
                }
            )
        }

        // 6. Dashboard (MainScreen)
        composable<DashboardRoute> {
            MainScreen(
                onLogout = {
                    navController.navigate(LoginRoute) {
                        popUpTo(DashboardRoute) { inclusive = true }
                    }
                },
                onNavigateToNewTransaction = { navController.navigate(NewTransactionRoute) }
            )
        }

        // 7. Nueva Transacción
        composable<NewTransactionRoute> {
            NewTransactionScreen(
                onNavigateBack = { navController.popBackStack() },
                onSaveTransaction = {
                    // Aquí a futuro guardaremos en la base de datos.
                    // Por ahora, solo volvemos atrás.
                    navController.popBackStack()
                }
            )
        }
    }
}