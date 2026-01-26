package com.example.languageapp.comp.presentation.signIn

sealed class SignInEvent {

    data class EnteredEmail(val email : String) : SignInEvent()

    data class EnteredPassword(val password : String) : SignInEvent()

    object SignIn : SignInEvent()
}