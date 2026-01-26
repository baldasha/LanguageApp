package com.example.languageapp.comp.domain.validation

class ValidateRepeatedPassword {
    fun execute(repeatedPassword: String, password: String) : ValidationResult {
        if(repeatedPassword != password){
            return ValidationResult(success = false, errorMessage = "Both passwords should be same")
        }
        return ValidationResult(success = true)
    }
}