// This is the interface that will be used to make the network call to the API
//This interface tells Retrofit how to make API requests.
//@GET annotation specifies the API endpoint.
//Call<NewsResponse> ensures we receive a structured response.
package com.example.newsapp.model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("User-Agent: Mozilla/5.0")
    @GET("top-headlines")
    fun getNews(
        //The @Query annotation is used to specify query parameters for the API request.
        //The query parameters are p assed as arguments to the getNews method.
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "05c8c1700fbf4911923d4c0bb7b45485"
    ): Call<NewsResponse>
}
//The getNews method returns a Call<NewsResponse>,
// indicating that the response will be parsed into a NewsResponse object.
//This interface tells Retrofit how to make the API request. When the getNews method is called,
// Retrofit will create an HTTP request to the specified endpoint and return the response wrapped in a Call object.

//In the context of the project, this ApiService interface is likely used by a repository or a ViewModel to fetch data from the API.
// For example, a repository class might use the ApiService to get news data and then provide it to the ViewModel, which in turn updates the UI.