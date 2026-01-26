package com.example.languageapp.comp.presentation.onBoarding

data class OnBoardingState(
    val currentScreen: Int = 0,
    val isOnBoardingComplete: Boolean = false,
)