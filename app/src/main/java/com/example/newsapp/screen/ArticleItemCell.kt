package com.example.newsapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import com.example.newsapp.Article

@Composable
fun NewsItemCell(
    article: Article,
    onClick: (String) -> Unit
    ) {
    Column(
        modifier = Modifier.clickable { onClick(article.url) }
    ){
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                fontSize = 5.em
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Row {
                Text(text = article.source)
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(text = article.date)
            }
        }
    }
    Divider()
    Spacer(modifier = Modifier.padding(vertical = 5.dp))
}