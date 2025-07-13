//We will define data classes to map API responses.
//single news model
package com.example.newsapp.model

//This maps JSON API responses to Kotlin objects.
//Converts the JSON API response into Kotlin objects.
//The NewsResponse data class represents the response from the API.

//Main API response model
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)
//Individual news article model
data class News(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
// Source information model
data class Source(
    val id: String?,
    val name: String?
)
