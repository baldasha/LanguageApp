package com.example.languageapp.comp.presentation.signIn.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.ui.theme.LanguageAppTheme
import com.example.languageapp.ui.theme.LightTextPrimary

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    keyboardType: KeyboardType,
    error: String?,
    caption: String
    ){
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(vertical = 12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = caption,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            isError = error != null,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF4F4F4),
                unfocusedContainerColor = Color(0xFFF4F4F4),
                disabledContainerColor = Color(0xFFF4F4F4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedTextColor = LightTextPrimary,
                unfocusedTextColor = LightTextPrimary,
                errorContainerColor = Color(0xFFF4F4F4)
            ),
            placeholder = {
                Text(
                    text = label,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                    color = LightTextPrimary.copy(alpha = 0.5F),
                    fontSize = 15.sp
                )
            },
            visualTransformation = if(isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            trailingIcon = {
                if(isPassword) {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if(isPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null,
                        )
                    }
                }
            }
        )
        error?.let { 
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                text = it,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview(){
    LanguageAppTheme() {
        CustomTextField(
            modifier = Modifier,
            label = "Email",
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Email,
            error = null,
            caption = "Email Address"
        )
    }

}