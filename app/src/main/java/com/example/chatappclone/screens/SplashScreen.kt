package com.example.chatappclone.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.chatappclone.R
import kotlinx.coroutines.delay
import com.example.chatappclone.navigation.Screen
import com.example.chatappclone.ui.theme.Primary

import com.example.chatappclone.util.SharedHelper

@Preview
@Composable
fun PreviewSplash() {
    SplashScreen(navController = rememberNavController())
}

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        delay(5000)
        if (SharedHelper.getInstance(context).getKey() == "")
            navController.navigate(Screen.Login.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        else {
            navController.navigate(Screen.Home.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.logo, imageLoader),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
            )


                Text(
                    text = "What's Up",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
}

