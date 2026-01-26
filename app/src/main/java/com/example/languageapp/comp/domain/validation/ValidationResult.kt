package com.example.languageapp.comp.domain.validation

data class ValidationResult (
    val success : Boolean,
    val errorMessage: String? = null
)
