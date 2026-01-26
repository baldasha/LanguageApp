package com.example.languageapp.comp.presentation.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languageapp.comp.presentation.profile.Buttons
import com.example.languageapp.ui.theme.ButtonBlue
import com.example.languageapp.ui.theme.LanguageAppTheme
import com.example.languageapp.ui.theme.LightOnSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectScreen(
    onSubmit : () -> Unit
) {
    val languages = listOf(
        "Russian",
        "English",
        "Chinese",
        "Belarus",
        "Kazakh",
        "Spanish"
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Language select",
                        color = LightOnSecondary,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        letterSpacing = 1.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "What is your Mother language?",
                fontSize = 22.sp,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 28.sp,
                letterSpacing = 1.sp
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(languages) { index, language ->
                    SelectButton(
                        text = language,
                        isSelected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
            Buttons(
                text = "Choose",
                onClick = onSubmit,
                color = ButtonBlue,
            )
        }
    }
}

@Composable
fun SelectButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color(0xFFF76400) else Color(0xFFFFF6EB),
                contentColor = Color.Black,
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Preview(modifier: Modifier = Modifier) {
    LanguageAppTheme() {
        LanguageSelectScreen(
            onSubmit = {}
        )
    }
}