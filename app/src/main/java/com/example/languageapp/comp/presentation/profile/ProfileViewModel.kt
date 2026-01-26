package com.example.languageapp.comp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.languageapp.core.auth.AuthStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authStorage: AuthStorage
) : ViewModel(){
    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        updateState()
    }


    private fun updateState(){
        val profile = authStorage.getData()

        _state.update {
            it.copy(
                profile = profile
            )
        }
    }

}