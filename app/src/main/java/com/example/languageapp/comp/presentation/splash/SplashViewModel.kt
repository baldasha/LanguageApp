package com.example.languageapp.comp.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.languageapp.core.auth.AuthStorage
import com.example.languageapp.core.data.OnBoardingPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.text.isNullOrBlank

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authStorage: AuthStorage,
    private val onBoardingPref: OnBoardingPref
) : ViewModel(){
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    init {
        isLoggedIn()
        isOnBoardingShowed()
    }

    private fun isLoggedIn(){
        _state.update {
            it.copy(
                isLoggedIn = authStorage.isDataThere()
            )
        }
    }

    private fun isOnBoardingShowed(){
        _state.update {
            it.copy(
                isOnBoardingShowed = onBoardingPref.getStatus()
            )
        }
    }



}