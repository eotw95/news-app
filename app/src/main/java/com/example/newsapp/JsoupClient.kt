package com.example.newsapp

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.Optional

class JsoupClient {
    companion object {
        fun fromUrl(url: String): Optional<Document>{
            val result = Jsoup.connect(url).get()
            return Optional.ofNullable(result)
        }
    }
}