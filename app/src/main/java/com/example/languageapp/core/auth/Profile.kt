package com.example.languageapp.core.auth

import com.example.languageapp.comp.presentation.signUp.SignUpEvent

data class Profile(
    val email: String?,
    val name: String?,
    val password: String?,
    val imageUri: String? = null
)
