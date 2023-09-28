package com.example.newsapp

import android.os.Build
import androidx.annotation.RequiresApi
import org.jsoup.nodes.Element
import java.util.Optional
import kotlin.streams.toList

object HtmlParser {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNewsList(): List<Article> {
        val url = "https://news.yahoo.co.jp/ranking/access/news"
        val document = JsoupClient.fromUrl(url)
        val tag = "a.newsFeed_item_link"
        val listElement = document.map { it.select(tag).stream().toList() }.orElse(listOf())

        return listElement.map { parseItem(it) }
    }

    private fun parseItem(element: Element): Article {
        val url = element.href()
        val title = element.firstText("div.newsFeed_item_title")
        val source = element.firstText("span.newsFeed_item_media")
        val date = element.firstText("time.newsFeed_item_date")
        val imageUrl = element.selectFirstOpt("img")
            .map { it.attr("src") }.orElse("").substringBefore("?")

        return Article(title, url, source, date, imageUrl)
    }
}

fun Element.selectFirstOpt(query: String): Optional<Element> =
    Optional.ofNullable(this.select(query).first())

fun Element.href(): String = this.attr("href")

fun Element.firstText(query: String): String =
    this.selectFirstOpt(query).map { it.text() }.orElse("")
