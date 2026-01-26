package com.example.languageapp.comp.presentation.signUp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.languageapp.comp.presentation.signIn.components.CustomTextField
import com.example.languageapp.ui.theme.ButtonBlue
import com.example.languageapp.ui.theme.LightOnSecondary
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpRoot(
    viewModel: SignUpViewModel1 = hiltViewModel(),
    onLogInClick: () -> Unit,
    onSignUp: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {

                is UiEvent.Failure -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                is UiEvent.Success -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_LONG
                    ).show()
                    onSignUp()
                }
            }
        }
    }

    when{
        !state.toSecondScreen -> {
            SignUpScreen1(
                state = state,
                onLogInClick = onLogInClick,
                onEvent = { event ->
                    viewModel.event(event)
                }
            )
        }
        state.toSecondScreen -> {
            SignUpScreen2(
                state = state,
                onLogInClick = onLogInClick,
                onEvent = { event ->
                    viewModel.event(event)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen1(
    state : SignUpState1,
    onLogInClick: () -> Unit,
    onEvent: (SignUpEvent) -> Unit

) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Signup",
                        color = LightOnSecondary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onLogInClick) {
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Create an Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                label = "Your First Name",
                value = state.firstName,
                onValueChange = {
                    onEvent(SignUpEvent.EnteredFirstName(it))
                },
                keyboardType = KeyboardType.Email,
                error = state.firstNameError,
                caption = "First Name"
            )


            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                label = "Your Last Name",
                value = state.lastName,
                onValueChange = {
                    onEvent(SignUpEvent.EnteredLastName(it))
                },
                keyboardType = KeyboardType.Email,
                error = state.lastNameError,
                caption = "Last Name"
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                label = "Email",
                value = state.email,
                onValueChange = {
                    onEvent(SignUpEvent.EnteredEmail(it))
                },
                keyboardType = KeyboardType.Email,
                error = state.emailError,
                caption = "Email Address"
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { onEvent(SignUpEvent.Continue) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            val annotatedString = buildAnnotatedString {
                append("Already you member? ")
                withStyle(
                    style = SpanStyle(
                        color = ButtonBlue,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Login")
                }
            }

            Text(
                text = annotatedString,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.clickable { onLogInClick() }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(SignUpState1()) }
    SignUpScreen1(
        state,
        {},
        {}
    )
}