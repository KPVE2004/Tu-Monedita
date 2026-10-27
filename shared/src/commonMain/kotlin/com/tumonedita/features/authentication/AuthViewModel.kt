package com.tumonedita.shared.features.authentication

import androidx.lifecycle.ViewModel
import com.tumonedita.shared.data.AuthRepository

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    fun login(email: String, onSuccess: () -> Unit) {
        if (email.isNotBlank()) {
            repository.saveSession(email)
            onSuccess()
        }
    }

    fun register(email: String, onSuccess: () -> Unit) {
        if (email.isNotBlank()) {
            repository.saveSession(email)
            onSuccess()
        }
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
}