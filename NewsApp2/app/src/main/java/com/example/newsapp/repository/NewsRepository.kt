
package com.example.newsapp.repository

import com.example.newsapp.model.ApiService
import com.example.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
//This class is responsible for fetching news data from the API.
//It uses Retrofit to make network requests and fetch data from the API.
class NewsRepository {
    //Retrofit is a type-safe HTTP client for Android and Java.
    // Before using Retrofit, you need to configure it with the base URL of the API and the converter factory for parsing JSON responses.
    //Retrofit.Builder() is used to create a Retrofit instance.
    //The baseUrl() method specifies the base URL of the API.
    //The addConverterFactory() method specifies the converter factory to use for parsing JSON responses.
    //The build() method creates the Retrofit instance.
    //The ApiService interface is used to define the API endpoints and request methods.
    //The create() method creates an implementation of the ApiService interface.
    // The fetchNews() method returns a Call object that can be used to make the network request and fetch news data.
    // The ApiService interface defines the getNews() method, which specifies the API endpoint and request parameters.
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val apiService = retrofit.create(ApiService::class.java)

    //call<NewsResponse> is used to fetch news data from the API.
    //The getNews() method in the ApiService interface is called to make the network request.
    fun fetchNews(): Call<NewsResponse> {
        return apiService.getNews()
    }
}
