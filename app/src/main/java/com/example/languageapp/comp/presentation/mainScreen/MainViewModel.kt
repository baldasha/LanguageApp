package com.example.languageapp.comp.presentation.mainScreen

import com.example.languageapp.R
import androidx.lifecycle.ViewModel
import com.example.languageapp.comp.presentation.mainScreen.components.TopUser
import com.example.languageapp.core.auth.AuthStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authStorage: AuthStorage
) : ViewModel(){
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        updateState()
    }

    private fun updateState(){
        val profileData = authStorage.getData()
        val topUsers = listOf(
            TopUser(name = "Vincent van Gogh", points = 12, avatar = R.drawable.vangogh),
            TopUser(name = "Dmitri Ivanovich Mendeleev", points = 10, avatar = R.drawable.dmitri),
            TopUser(name = "Vlad Tepes", points = 8, avatar = R.drawable.vlad),
        )

        _state.update {
            it.copy(
                profile = profileData,
                topUsers = topUsers
            )
        }
    }
}