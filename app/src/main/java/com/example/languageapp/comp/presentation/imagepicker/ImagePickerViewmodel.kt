package com.example.languageapp.comp.presentation.imagepicker

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.core.auth.AuthStorage
import com.example.notesappsignup.presentation.signIn.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor(
    private val authStorage: AuthStorage
) : ViewModel() {

    private val _state = MutableStateFlow(ImagePickerUiState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: ImagePickerEvent) {
        when (event) {
            is ImagePickerEvent.PickImage -> {
                _state.update { it.copy(selectedImageUri = event.uri, error = null) }
                Log.d("image", event.uri.toString())
            }
            is ImagePickerEvent.SaveImage -> saveImage()
        }
    }



    private fun saveImage() {
        viewModelScope.launch {
            try {
                val imageUriString = _state.value.selectedImageUri?.toString()

                authStorage.saveImageUri(
                    imageUri = imageUriString
                )
                _uiEvent.emit(UiEvent.Success("Image Saved"))
            } catch (e: Exception) {
                _uiEvent.emit(UiEvent.Failure(e.message?:"Failed to save image"))
            }
        }
    }
}