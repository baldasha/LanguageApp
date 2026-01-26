package com.example.languageapp.comp.presentation.imagepicker

import android.net.Uri

data class ImagePickerUiState(
    val selectedImageUri: Uri? = null,
    val error: String? = null,
)