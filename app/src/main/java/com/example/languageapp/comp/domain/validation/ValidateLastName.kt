package com.example.languageapp.comp.domain.validation

class ValidateLastName {
    fun execute (lastName : String) : ValidationResult {
        if (lastName.isBlank()){
            return ValidationResult(success = false, errorMessage = "Last name can't be blank")
        }
        return ValidationResult(success = true)
    }
}