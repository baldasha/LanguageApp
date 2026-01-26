package com.example.languageapp.comp.presentation.onBoarding

sealed class OnBoardingEvent {
    object NextScreen : OnBoardingEvent()
    object SkipOnBoarding : OnBoardingEvent()
    object CompleteOnBoarding : OnBoardingEvent()
}