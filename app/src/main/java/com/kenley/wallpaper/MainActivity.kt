package com.kenley.wallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kenley.wallpaper.ui.theme.view.category.PictureListScreen
import com.kenley.wallpaper.ui.theme.WallpaperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WallpaperTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController, startDestination = Screens.LandingScreen.route) {
                    // add all destinations here
                    composable(route = Screens.LandingScreen.route) {
                        LandingScreenLayout(
                            navController)
                    } // home destination
                    composable(route = Screens.PictureListScreen.route) {
                        PictureListScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun LandingScreenLayout(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
//            .background(Color.Yellow)
    ) {
        Image(
            painter = painterResource(R.drawable.welocme), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)

        Button(
            onClick = {
                navController.navigate(Screens.PictureListScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, bottom = 30.dp, end = 20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text("Start Explore", style = TextStyle(fontSize = 20.sp), color = Color.White)
        }
    }
}

@Preview
@Composable
fun PreviewFullScreenLayout() {
    val navController = rememberNavController()
    LandingScreenLayout(navController)
}