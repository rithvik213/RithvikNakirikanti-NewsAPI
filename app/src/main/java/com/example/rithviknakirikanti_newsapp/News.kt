package com.example.rithviknakirikanti_newsapp

import java.io.Serializable


data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Serializable

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) : Serializable

data class Source(
    val id: String?,
    val name: String
) : Serializable
