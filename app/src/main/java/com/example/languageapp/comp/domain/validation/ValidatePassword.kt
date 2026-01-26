package com.example.languageapp.comp.domain.validation

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.isBlank()){
            return ValidationResult(success = false, errorMessage = "password can't be blank")
        }
        if(password.length < 8){
            return ValidationResult(
                success = false,
                errorMessage = "password length shouldn't be less then 8"
            )
        }
        val containsDigitsAndLetters = password.any{ it.isDigit() } && password.any { it.isLetter() }
        if (!containsDigitsAndLetters){
            return ValidationResult(
                success = false,
                errorMessage = "password should contain both letters and digits"
            )
        }
        return ValidationResult(success = true)
    }
}