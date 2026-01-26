package com.example.languageapp.comp.presentation.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.languageapp.R
import com.example.languageapp.core.auth.Profile
import com.example.languageapp.ui.theme.ButtonBlue

@Composable
fun ProfileScreen(
    viewModel : ProfileViewModel = hiltViewModel(),
    onLogout : () -> Unit,
    onToggleTheme : () -> Unit,
    onImageChange : () -> Unit,
    onLanguageChange : () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ProfileSnap(profile = state.profile)

        Spacer(modifier = Modifier.height(250.dp))

        Buttons(text = "Switch to Dark", color = ButtonBlue, onClick = onToggleTheme)
        Buttons(text = "Change mother language", color = ButtonBlue, onClick = onLanguageChange)
        Buttons(text = "Change your image", color = ButtonBlue, onClick = onImageChange)
        Buttons(text = "Logout", color = Color.LightGray, onClick = onLogout)

    }
}

@Composable
fun ProfileSnap(profile: Profile?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(231.dp)
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.Start
    ) {
        val imageUriString = profile?.imageUri
        val imageUri = imageUriString?.let { Uri.parse(it) }

        AsyncImage(
            modifier = Modifier
                .padding(start = 24.dp, top = 44.dp)
                .size(134.dp)
                .clip(CircleShape),
            model = imageUri,
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.profileavatar),
            error = painterResource(R.drawable.profileavatar)
        )

        Text(
            modifier = Modifier
                .padding(start = 24.dp, top = 8.dp),
            text = "Your profile, ${profile?.name}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimary,
            lineHeight = 28.sp,
            letterSpacing = 1.sp,
        )
    }
}

@Composable
fun Buttons(text : String, color: Color, onClick : () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {onClick()},
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = color),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview3(modifier: Modifier = Modifier) {
    ProfileScreen(onLogout = {}, onToggleTheme = {}, onImageChange = {}, onLanguageChange = {})
}