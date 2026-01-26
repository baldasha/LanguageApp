package com.example.notesappsignup.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.comp.domain.validation.ValidateEmail
import com.example.languageapp.comp.domain.validation.ValidatePassword
import com.example.languageapp.comp.presentation.signIn.SignInEvent
import com.example.languageapp.comp.presentation.signIn.SignInState
import com.example.languageapp.core.auth.AuthStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validatePassword: ValidatePassword,
    private val validateEmail: ValidateEmail,
    private val authStorage: AuthStorage
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun event(event: SignInEvent) {
        when (event) {
            is SignInEvent.EnteredPassword -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }
            is SignInEvent.EnteredEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }
            SignInEvent.SignIn -> signIn()
        }
    }

    private fun signIn() {
        val emailResult = validateEmail.execute(_state.value.email)
        val passwordResult = validatePassword.execute(_state.value.password)
        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.success }
        if (hasError){
            _state.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }
        viewModelScope.launch {
            if(authStorage.checkData(email = _state.value.email, password = _state.value.password))
                _uiEvent.emit(UiEvent.Success("Log In Successful"))
            else{
                _uiEvent.emit(UiEvent.Failure("Email or Password Don't Match"))
            }
        }

    }
}

sealed class UiEvent{
    data class Success(val message : String) : UiEvent()
    data class Failure(val message : String) : UiEvent()
}