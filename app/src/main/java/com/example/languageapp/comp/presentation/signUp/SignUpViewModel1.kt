package com.example.languageapp.comp.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageapp.comp.domain.validation.ValidateEmail
import com.example.languageapp.comp.domain.validation.ValidateFirstName
import com.example.languageapp.comp.domain.validation.ValidateLastName
import com.example.languageapp.comp.domain.validation.ValidatePassword
import com.example.languageapp.comp.domain.validation.ValidateRepeatedPassword
import com.example.languageapp.comp.domain.validation.ValidateTermsAccepted
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
class SignUpViewModel1 @Inject constructor(
    private val validateFirstName: ValidateFirstName,
    private val validateLastName: ValidateLastName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val validateTermsAccepted: ValidateTermsAccepted,
    private val authStorage: AuthStorage

) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState1())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun event(event: SignUpEvent) {
        when (event) {

            is SignUpEvent.EnteredFirstName -> {
                _state.update {
                    it.copy(
                        firstName = event.firstName,
                        firstNameError = null
                    )
                }
            }

            is SignUpEvent.EnteredLastName -> {
                _state.update {
                    it.copy(
                        lastName = event.lastName,
                        lastNameError = null
                    )
                }
            }

            is SignUpEvent.EnteredEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }

            SignUpEvent.Continue -> continueAuth()

            is SignUpEvent.EnteredPassword -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }

            is SignUpEvent.EnteredRepeatedPassword -> {
                _state.update {
                    it.copy(
                        repeatedPassword = event.repeatedPassword,
                        repeatedPasswordError = null
                    )
                }
            }

            is SignUpEvent.AcceptedTerms -> {
                _state.update {
                    it.copy(
                        isAccepted = event.isAccepted,
                        acceptedError = null
                    )
                }
            }
            SignUpEvent.SignUp -> signUp()

            is SignUpEvent.ToGoBack -> {
                _state.update {
                    it.copy(
                        toSecondScreen = false
                    )
                }
            }
        }
    }

    private fun continueAuth() {
        val firstNameResult = validateFirstName.execute(_state.value.firstName)
        val lastNameResult = validateLastName.execute(_state.value.lastName)
        val emailResult = validateEmail.execute(_state.value.email)
        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult
        ).any{!it.success}
        if(hasError){
            _state.update {
                it.copy(
                    firstNameError = firstNameResult.errorMessage,
                    lastNameError = firstNameResult.errorMessage,
                    emailError = emailResult.errorMessage
                )
            }
            return
        }
        _state.update {
            it.copy(
                toSecondScreen = true
            )
        }

    }

    private fun signUp() {
        val passwordResult = validatePassword.execute(_state.value.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(_state.value.repeatedPassword, _state.value.password)
        val acceptedResult = validateTermsAccepted.execute(_state.value.isAccepted)

        val hasError = listOf(
            passwordResult,
            repeatedPasswordResult,
            acceptedResult
        ).any {!it.success}
        if(hasError){
            _state.update {
                it.copy(
                    passwordError = passwordResult.errorMessage,
                    repeatedPasswordError = repeatedPasswordResult.errorMessage,
                    acceptedError = acceptedResult.errorMessage
                )
            }
            return
        }

        authStorage.saveData(
            email = _state.value.email,
            password = _state.value.password,
            name = _state.value.firstName
            )

        viewModelScope.launch {
            _uiEvent.emit(UiEvent.Success("Sign Up Successful"))
        }

    }
}

sealed class UiEvent{
    data class Success(val message : String) : UiEvent()
    data class Failure(val message : String) : UiEvent()
}