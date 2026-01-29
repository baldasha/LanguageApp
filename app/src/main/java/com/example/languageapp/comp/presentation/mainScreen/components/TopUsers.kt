package com.example.languageapp.comp.presentation.mainScreen.components

import androidx.annotation.DrawableRes

data class TopUser(
    val name : String,
    val points : Int,
    @DrawableRes val avatar : Int,
)

data class WidgetTopUser(
    val number : String,
    val name : String,
    val points : Int,
    val avatar : Int,
    val isSelected : Boolean
)
