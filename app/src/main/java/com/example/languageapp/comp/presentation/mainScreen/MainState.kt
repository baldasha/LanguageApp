package com.example.languageapp.comp.presentation.mainScreen

import com.example.languageapp.comp.presentation.mainScreen.components.TopUser
import com.example.languageapp.core.auth.Profile

data class MainState(
    val profile : Profile? = null,
    val topUsers : List<TopUser> = emptyList(),
)
