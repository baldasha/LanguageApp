package com.example.languageapp.comp.presentation.mainScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.languageapp.R
import com.example.languageapp.comp.presentation.mainScreen.components.ExerciseCard
import com.example.languageapp.comp.presentation.mainScreen.components.TopUser
import com.example.languageapp.comp.presentation.mainScreen.components.TopUserItem
import com.example.languageapp.core.auth.Profile

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onProfileClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header(
            profile = state.profile,
            onProfileClick
            )
        TopUsers(topUsers = state.topUsers)
        ExercisesGrid()
    }
}

@Composable
fun Header(
    profile: Profile?,
    onProfileClick : () -> Unit
    ) {
        val context = LocalContext.current
        val imageUriString = profile?.imageUri
        //val imageUri = imageUriString?.let { Uri.parse(it) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
                .clickable{
                    onProfileClick()
                }
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f), CircleShape)
            ){
                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape),
                    model = ImageRequest.Builder(context)
                        .data(imageUriString)
                        .build(),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.profileavatar),
                    error = painterResource(R.drawable.profileavatar)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Hello, ${profile?.name ?: "User"}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Are you ready for learning today?",
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                fontSize = 14.sp
            )

            }
        }


@Composable
fun TopUsers(topUsers: List<TopUser>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Top users",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        topUsers.forEach { user ->
            TopUserItem(user = user)
        }
    }
}

@Composable
fun ExercisesGrid() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Available exercises",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { ExerciseCard(emoji = R.drawable.animal, title = "Guess the animal", color = Color(0xFF5C7CFA)) }
            item { ExerciseCard(emoji = R.drawable.word, title = "Word practice", color = Color(0xFFD6185D)) }
            item { ExerciseCard(emoji = R.drawable.audition, title = "Audition", color = Color(0xFFF76707)) }
            item { ExerciseCard(emoji = R.drawable.game, title = "Game", color = Color(0xFF5BA890)) }
        }
    }
}


