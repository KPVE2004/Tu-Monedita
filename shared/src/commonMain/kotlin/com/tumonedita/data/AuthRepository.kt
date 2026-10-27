package com.tumonedita.shared.data

import com.russhwolf.settings.Settings

class AuthRepository {
    private val settings: Settings = Settings()

    fun saveSession(email: String) {
        settings.putString("user_email", email)
        settings.putBoolean("is_logged_in", true)
    }

    fun isLoggedIn(): Boolean {
        return settings.getBoolean("is_logged_in", false)
    }

    fun logout() {
        settings.clear()
    }
}