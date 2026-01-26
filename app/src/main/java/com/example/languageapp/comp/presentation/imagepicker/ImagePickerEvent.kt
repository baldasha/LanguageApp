package com.example.languageapp.comp.presentation.imagepicker

import android.net.Uri

sealed class ImagePickerEvent {
    data class PickImage(val uri : Uri?) : ImagePickerEvent()
    object SaveImage : ImagePickerEvent()
}