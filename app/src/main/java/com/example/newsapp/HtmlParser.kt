package com.example.newsapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.jsoup.nodes.Element
import java.util.Optional
import kotlin.streams.toList

object HtmlParser {
    @RequiresApi(Build.VERSION_CODES.O)
    fun get() {
        val url = "https://news.yahoo.co.jp/ranking/access/news"
        val document = JsoupClient.fromUrl(url)
        val tag = "a.newsFeed_item_link"
        val listElement = document.map { it.select(tag).stream().toList() }.orElse(listOf())
        val newsList: List<NewsItem> = listElement.map { parseItem(it) }
        Log.d("HtmlParser", "newsList=$newsList")

//        val gson = GsonBuilder().setPrettyPrinting().create()
//        gson.toJson(newsList, FileWriter(Paths.get("news.json").toFile()))
    }

    private fun parseItem(element: Element): NewsItem {
        val url = element.href()
        val title = element.firstText("div.newsFeed_item_title")
        val source = element.firstText("span.newsFeed_item_media")
        val date = element.firstText("time.newsFeed_item_date")
        val imageUrl = element.selectFirstOpt("img")
            .map { it.attr("src") }.orElse("").substringBefore("?")

        return NewsItem(title, url, source, date, imageUrl)
    }
}

fun Element.selectFirstOpt(query: String): Optional<Element> =
    Optional.ofNullable(this.select(query).first())

fun Element.href(): String = this.attr("href")

fun Element.firstText(query: String): String =
    this.selectFirstOpt(query).map { it.text() }.orElse("")
