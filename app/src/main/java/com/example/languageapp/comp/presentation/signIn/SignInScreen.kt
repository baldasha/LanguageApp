package com.example.languageapp.comp.presentation.signIn

import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.languageapp.R
import com.example.languageapp.comp.presentation.signIn.components.CustomTextField
import com.example.languageapp.ui.theme.ButtonBlue
import com.example.languageapp.ui.theme.LanguageAppTheme
import com.example.languageapp.ui.theme.LightOnSecondary
import com.example.notesappsignup.presentation.signIn.SignInViewModel
import com.example.notesappsignup.presentation.signIn.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInRoot(
    viewModel: SignInViewModel = hiltViewModel(),
    onSignUpClick: () -> Unit,
    onSignInSuccess: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Success -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_LONG
                    ).show()
                    onSignInSuccess()
                }
                is UiEvent.Failure -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }

    when{
        state.isLoading -> {

        }
        else -> {
            SignInScreen(
                state = state,
                onSignUpClick = onSignUpClick,
                onEvent = { event ->
                    viewModel.event(event)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    state: SignInState,
    onSignUpClick: () -> Unit,
    onEvent: (SignInEvent) -> Unit,
) {




    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Login",
                        color = LightOnSecondary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = LightOnSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                modifier = Modifier
                    .size(120.dp),
                painter = painterResource(R.drawable.signinphoto),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "For free, join now and\nstart learning",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                label = "Email",
                value = state.email,
                onValueChange = {
                    onEvent(SignInEvent.EnteredEmail(it))
                },
                keyboardType = KeyboardType.Email,
                error = state.emailError,
                caption = "Email Adress"
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                label = "••••••••",
                value = state.password,
                onValueChange = { onEvent(SignInEvent.EnteredPassword(it)) },
                isPassword = true,
                keyboardType = KeyboardType.Password,
                error = state.passwordError,
                caption = "Password"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    onEvent(SignInEvent.SignIn)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                shape = RoundedCornerShape(12.dp),
                enabled = !state.isLoading
            ) {
                Text(
                    text = if (state.isLoading) "Login In..." else "Login In",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }

            val annotatedString = buildAnnotatedString {
                append("Not you member? ")
                withStyle(style = SpanStyle(color = ButtonBlue, fontWeight = FontWeight.Bold)) {
                    append("Signup")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = annotatedString,
                fontSize = 17.sp,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F),
                modifier = Modifier.clickable { onSignUpClick() },
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun Preview(modifier: Modifier = Modifier) {
    LanguageAppTheme(
        dynamicColor = false
    ) {
        var state by remember { mutableStateOf(SignInState()) }
        SignInScreen(
            onSignUpClick = {},
            state = state,
            onEvent = {}
        )
    }
}

