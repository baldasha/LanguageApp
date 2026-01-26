package com.example.languageapp.comp.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.core.data.OnBoardingPref
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingPref: OnBoardingPref
) : ViewModel() {

    private val _state = MutableStateFlow(OnBoardingState())
    val state = _state.asStateFlow()

    fun event(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.NextScreen -> nextScreen()
            OnBoardingEvent.SkipOnBoarding -> saveOnBoardingStatus()
            OnBoardingEvent.CompleteOnBoarding -> saveOnBoardingStatus()
        }
    }

    private fun nextScreen() {
        _state.update {
            it.copy(currentScreen = it.currentScreen + 1)
        }
    }


    private fun saveOnBoardingStatus() {
        onBoardingPref.saveStatus(true)
        _state.update { it.copy(isOnBoardingComplete = true) }
    }
}
