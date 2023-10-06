package com.example.newsapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.screen.ArticleGrid
import com.example.newsapp.screen.NewsDetail

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "newsList",
        builder = {
            composable("newsList") {
                ArticleGrid(
                    onClick = { url ->
                        navController.navigate("detail/$url")
                    }
                )
            }
            composable("detail/{url}") { navBackStackEntry ->
                navBackStackEntry.arguments?.getString("url")?.let { url ->
                    NewsDetail(
                        url = url,
                        onClickButton =  { navController.navigateUp() }
                    )
                } ?: run {
                    NewsDetail(
                        url = "https://news.yahoo.co.jp/ranking/access/news",
                        onClickButton =  { navController.navigateUp() }
                    )
                }
            }
        }
    )
}