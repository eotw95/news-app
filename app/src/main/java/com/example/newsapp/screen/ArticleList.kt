package com.example.newsapp.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.newsapp.HtmlParser
import com.example.newsapp.Article

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsList(
    onClick: (String) -> Unit
) {
    val initialValue = listOf(Article())
    var newsList by remember { mutableStateOf(initialValue) }
    Thread {
        newsList = HtmlParser.getNewsList()
        Log.d("MainActivity", "newsList=$$newsList")
    }.start()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(content = {
            items(newsList) { item ->
                NewsItemCell(
                    article = item,
                    onClick = onClick
                )
            }
        })
    }
}