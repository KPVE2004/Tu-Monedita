package com.tumonedita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.tumonedita.shared.navigation.AppNavigation
import com.tumonedita.shared.designsystem.TuMoneditaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            TuMoneditaTheme {
                AppNavigation()
            }
        }
    }
}