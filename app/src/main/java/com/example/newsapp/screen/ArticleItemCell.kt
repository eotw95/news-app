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
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsItemCell(
    article: Article,
    onClick: (String) -> Unit
    ) {
    Column(
        modifier = Modifier.clickable {
            val encordUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
            onClick(encordUrl)
        }
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
                fontSize = 3.em
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Row {
                Text(
                    text = article.source,
                    fontSize = 1.em
                )
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(
                    text = article.date,
                    fontSize = 1.em
                )
            }
        }
    }
}