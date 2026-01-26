package com.example.languageapp.comp.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.languageapp.comp.domain.repository.ThemeRepository

class ThemeRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : ThemeRepository {
    override fun observeTheme(): Boolean {
        return sharedPreferences.getBoolean("dark_theme", false)
    }

    override suspend fun setTheme(isDark: Boolean) {
        sharedPreferences.edit{
            putBoolean("dark_theme", isDark)
        }
    }
}