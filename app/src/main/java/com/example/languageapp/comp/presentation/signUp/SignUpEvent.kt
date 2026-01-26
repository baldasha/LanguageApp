package com.example.languageapp.comp.presentation.signUp

sealed class SignUpEvent {

    data class EnteredFirstName(val firstName : String) : SignUpEvent()
    data class EnteredLastName(val lastName : String) : SignUpEvent()

    data class EnteredEmail(val email : String) : SignUpEvent()

    object Continue : SignUpEvent()

    data class EnteredPassword(val password : String) : SignUpEvent()
    data class EnteredRepeatedPassword(val repeatedPassword : String) : SignUpEvent()
    data class AcceptedTerms(val isAccepted: Boolean) : SignUpEvent()

    object ToGoBack : SignUpEvent()

    object SignUp : SignUpEvent()

}