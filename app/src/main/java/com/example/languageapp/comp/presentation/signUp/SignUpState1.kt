package com.example.languageapp.comp.presentation.signUp

data class SignUpState1(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val password: String = "",
    val repeatedPassword: String = "",
    val isAccepted: Boolean = false,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
    val acceptedError: String? = null,
    val toSecondScreen: Boolean = false
)
