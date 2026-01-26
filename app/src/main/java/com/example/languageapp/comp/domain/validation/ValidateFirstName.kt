package com.example.languageapp.comp.domain.validation

class ValidateFirstName {
    fun execute (firstName : String) : ValidationResult {
        if (firstName.isBlank()){
            return ValidationResult(success = false, errorMessage = "First name can't be blank")
        }

        return ValidationResult(success = true)
    }
}