package com.maxi.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maxi.news.ui.screens.HeadlinesScreen
import com.maxi.news.ui.screens.NewsSourcesScreen
import com.maxi.news.ui.screens.OptionsScreen
import com.maxi.news.utils.Constants.HEADLINES_PATH
import com.maxi.news.utils.Constants.NEWS_SOURCES_PATH
import com.maxi.news.utils.Constants.OPTIONS_SCREEN_PATH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = OPTIONS_SCREEN_PATH) {
        composable(OPTIONS_SCREEN_PATH) {
            OptionsScreen { path: String ->
                if (path == HEADLINES_PATH) {
                    navController.navigate("$HEADLINES_PATH/${""}/${""}")
                } else {
                    navController.navigate(path)
                }
            }
        }

        composable("$HEADLINES_PATH/{language}/{source}",
            arguments = listOf(
                navArgument("language") {
                    type = NavType.StringType
                },
                navArgument("source") {
                    type = NavType.StringType
                }
            )
        ) {
            HeadlinesScreen()
        }

        composable(NEWS_SOURCES_PATH) {
            NewsSourcesScreen { source: String ->
                navController.navigate("$HEADLINES_PATH/${""}/${source}")
            }
        }
    }
}