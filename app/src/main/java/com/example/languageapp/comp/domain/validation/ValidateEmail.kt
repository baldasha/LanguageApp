package com.example.languageapp.comp.domain.validation

import android.util.Patterns

class ValidateEmail {
    fun execute(email : String) : ValidationResult {
        if(email.isBlank()){
            return ValidationResult(success = false, errorMessage = "Email can't be blank")
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                success = false,
                errorMessage = "The entered text is not an email"
            )
        }
        return ValidationResult(success = true)
    }
}