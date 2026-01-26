package com.example.languageapp.comp.presentation.signUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.comp.presentation.signIn.components.CustomTextField
import com.example.languageapp.ui.theme.ButtonBlue
import com.example.languageapp.ui.theme.LightOnSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen2(
    state : SignUpState1,
    onLogInClick : () -> Unit,
    onEvent: (SignUpEvent) -> Unit,
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
                    IconButton(onClick = {
                        onEvent(SignUpEvent.ToGoBack)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = LightOnSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
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
                text = "Choose a Password",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                label = "••••••••",
                value = state.password,
                onValueChange = { onEvent(SignUpEvent.EnteredPassword(it)) },
                isPassword = true,
                keyboardType = KeyboardType.Password,
                error = state.passwordError,
                caption = "Password"
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                label = "••••••••",
                value = state.repeatedPassword,
                onValueChange = { onEvent(SignUpEvent.EnteredRepeatedPassword(it)) },
                isPassword = true,
                keyboardType = KeyboardType.Password,
                error = state.repeatedPasswordError,
                caption = "Confirm Password"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Checkbox(
                    checked = state.isAccepted,
                    onCheckedChange = { onEvent(SignUpEvent.AcceptedTerms(it)) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = ButtonBlue,
                        uncheckedColor = ButtonBlue
                    )
                )

                val rulesText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = ButtonBlue)) {
                        append("I have made myself acquainted with the Rules ")
                    }
                    append("and accept all its provisions.")
                }

                Text(
                    text = rulesText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { onEvent(SignUpEvent.SignUp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text(
                    text = "Signup",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                    )
            }

            Spacer(modifier = Modifier.height(24.dp))

            val loginText = buildAnnotatedString {
                append("Already you member? ")
                withStyle(style = SpanStyle(color = ButtonBlue, fontWeight = FontWeight.Bold)) {
                    append("Login")
                }
            }

            Text(
                text = loginText,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable { onLogInClick }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}