package com.example.languageapp.comp.presentation.imagepicker

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.languageapp.R
import com.example.languageapp.comp.presentation.profile.Buttons
import com.example.languageapp.ui.theme.ButtonBlue
import com.example.languageapp.ui.theme.DarkBackground
import com.example.notesappsignup.presentation.signIn.UiEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePicker(
    onImageSaved: () -> Unit,
) {
    val viewModel: ImagePickerViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri : Uri? ->
            if (uri != null){
                context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                viewModel.onEvent(ImagePickerEvent.PickImage(uri = uri))
            }else{
                viewModel.onEvent(ImagePickerEvent.PickImage(uri = uri))
            }
        }
    )

    LaunchedEffect(Unit) {
        singlePhotoPickerLauncher.launch(
            arrayOf("image/*")
        )
    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Success -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_LONG
                    ).show()
                    onImageSaved()
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Your photo id gorgeous!",
                        color = Color.White,
                        fontSize = 22.sp,
                        letterSpacing = 1.sp,
                        lineHeight = 28.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .background(DarkBackground)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Jut resize photo \nfor fit in a square",
                color = Color.White,
                fontSize = 22.sp,
                letterSpacing = 1.sp,
                lineHeight = 28.sp
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(253.dp)
                    .padding(horizontal = 32.dp)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = state.selectedImageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.overlay_mask),
                    contentDescription = null,
                    alpha = 0.7f,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Buttons(
                    text = "Use That Image",
                    color = ButtonBlue,
                    onClick = {
                        viewModel.onEvent(ImagePickerEvent.SaveImage)
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewImagePicker() {
    ImagePicker(onImageSaved = {})
}