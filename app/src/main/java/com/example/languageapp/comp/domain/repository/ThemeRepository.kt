package com.example.languageapp.comp.domain.repository

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    fun observeTheme() : Boolean

    suspend fun setTheme(isDark : Boolean)

}