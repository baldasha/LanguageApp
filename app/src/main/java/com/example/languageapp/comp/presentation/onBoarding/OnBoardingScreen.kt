package com.example.languageapp.comp.presentation.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.languageapp.R
import com.example.languageapp.ui.theme.ButtonBlue
import com.example.languageapp.ui.theme.LanguageAppTheme

@Composable
fun OnBoardingRoot(
    onSignIn: () -> Unit,
    onLanguageSelect: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (state.currentScreen) {
        0 -> OnBoarding1(
            onNext = { viewModel.event(OnBoardingEvent.NextScreen) },
            onSignIn = {
                viewModel.event(OnBoardingEvent.SkipOnBoarding)
                onSignIn()
            }
        )
        1 -> OnBoarding2(
            onNext = { viewModel.event(OnBoardingEvent.NextScreen) },
            onSignIn = {
                viewModel.event(OnBoardingEvent.SkipOnBoarding)
                onSignIn()
            }
        )
        2 -> OnBoarding3(
            onSignIn = {
                viewModel.event(OnBoardingEvent.CompleteOnBoarding)
                onSignIn()
            },
            onLanguageSelect = {
                viewModel.event(OnBoardingEvent.CompleteOnBoarding)
                onLanguageSelect()
            }
        )
    }
}

@Composable
fun OnBoarding1(
    onNext : () -> Unit,
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.onboarding1),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(60.dp))

        Image(
            modifier = Modifier.size(35.dp),
            painter = painterResource(R.drawable.slider1),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = "Confidence in your words",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "With conversation-based learning, \n you'll be talking from lesson one",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F)
        )

        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                onNext()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {onSignIn()},
            text = "Skip onboarding",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun OnBoarding2(
    onNext: () -> Unit,
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.onboarding2),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(60.dp))

        Image(
            modifier = Modifier.size(35.dp),
            painter = painterResource(R.drawable.slider2),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = "Take your time to learn",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Develop a habit of learning and \n make it a part of your daily routine",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F)
        )

        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                onNext()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {onSignIn()},
            text = "Skip onboarding",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun OnBoarding3(
    onSignIn: () -> Unit,
    onLanguageSelect: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.onboarding3),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(60.dp))

        Image(
            modifier = Modifier.size(35.dp),
            painter = painterResource(R.drawable.slider3),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = "The lessons you need to learn",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Using a variety of learning styles to learn \n and retain",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F)
        )

        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                onLanguageSelect()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Choose a language",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {onSignIn()},
            text = "Skip onboarding",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
fun Preview4 (modifier: Modifier = Modifier) {
    LanguageAppTheme() {
        OnBoarding3(
            onSignIn = {},
            onLanguageSelect = {}
        )
    }
}