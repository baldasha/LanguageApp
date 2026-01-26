package com.example.languageapp.comp.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.comp.domain.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val repository: ThemeRepository
) : ViewModel(){
    private val _state = MutableStateFlow(ThemeState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                isDark = repository.observeTheme()
            )
        }
    }

    fun onEvent(event: ThemeEvent){
        when(event){
            ThemeEvent.Toggle -> {
                val newValue = !_state.value.isDark
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isDark = newValue
                        )
                    }
                    repository.setTheme(isDark = newValue)
                }
            }
        }
    }

}