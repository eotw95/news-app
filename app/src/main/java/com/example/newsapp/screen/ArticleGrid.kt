package com.example.newsapp.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.newsapp.HtmlParser
import com.example.newsapp.Article

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleGrid(
    onClick: (String) -> Unit
) {
    val initialValue = listOf(Article())
    var newsList by remember { mutableStateOf(initialValue) }
    Thread {
        newsList = HtmlParser.getNewsList()
        Log.d("MainActivity", "newsList=$$newsList")
    }.start()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 194.dp)
    ) {
        items(newsList) { article ->
            NewsItemCell(
                article = article,
                onClick = onClick
            )
        }
    }
}