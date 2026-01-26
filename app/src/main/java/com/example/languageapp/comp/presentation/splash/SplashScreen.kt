package com.example.languageapp.comp.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.languageapp.R
import com.example.languageapp.ui.theme.LightPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onLoginScreen: () -> Unit,
    onOnBoardingScreen: () -> Unit,
    onMainScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val size = remember { Animatable(initialValue = 75.dp, Dp.VectorConverter) }

    LaunchedEffect(key1 = Unit) {
        launch {
            while (true) {
                size.animateTo(
                    targetValue = 200.dp,
                    animationSpec = tween(durationMillis = 800, easing = LinearEasing)
                )
                size.animateTo(
                    targetValue = 150.dp,
                    animationSpec = tween(durationMillis = 800, easing = LinearEasing)
                )
            }
        }
        delay(1500)
        when (state.isLoggedIn) {
            true -> {
                onMainScreen()
            }
            false if state.isOnBoardingShowed == true -> {
                onLoginScreen()
            }
            else -> {
                onOnBoardingScreen()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPrimary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(size.value)
        )
    }
}