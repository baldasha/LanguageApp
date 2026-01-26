package com.example.languageapp.comp.domain.validation

class ValidateTermsAccepted {
    fun execute(isAccepted: Boolean): ValidationResult {
        if (!isAccepted) {
            return ValidationResult(
                success = false,
                errorMessage = "You must accept the terms and conditions"
            )
        }
        return ValidationResult(success = true)
    }
}