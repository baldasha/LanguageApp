package com.example.languageapp.comp.presentation.signIn

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
)
