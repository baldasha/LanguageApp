package com.example.languageapp

import kotlinx.serialization.Serializable

sealed class Destinations() {

    @Serializable
    object SplashScreen : Destinations()

    @Serializable
    object OnBoardingScreen : Destinations()

    @Serializable
    object SignUpScreen1 : Destinations()

    @Serializable
    object SignInScreen : Destinations()

    @Serializable
    object MainScreen : Destinations()

    @Serializable
    object ProfileScreen : Destinations()

    @Serializable
    object OnBoardingLanguageSelectScreen : Destinations()

    @Serializable
    object ProfileLanguageSelectScreen : Destinations()

    @Serializable
    object  ImagePicker : Destinations()

}