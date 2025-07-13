package com.example.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.controller.NewsAdapter
import com.example.newsapp.model.ApiService
import com.example.newsapp.model.News
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load NewsListFragment in the MainActivity
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment()) // ✅ Adds Fragment
            .commit()
    }
}

//
//    //lateinit is used to tell the compiler that the variable will be initialized later.
//    //This is useful when you cannot initialize a variable immediately, but you know it will be initialized before you use it.
//    //recyclerView is the RecyclerView instance
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var newsAdapter: NewsAdapter
//    private lateinit var progressBar: View
//
//    private val newsList = mutableListOf<News>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //inflates the activityMain.xml
//        setContentView(R.layout.activity_main)
//       // This method returns a reference to the RecyclerView widget, which is then assigned
//        // to the recyclerView variable. The RecyclerView is a flexible view for providing a limited window into a large data set.
//        //initialize ui elements
//        recyclerView = findViewById(R.id.recyclerView)
//        progressBar = findViewById(R.id.progressBar)
//        //sets the layout manager for the recycler view
//        //The layout manager is responsible for positioning items in the RecyclerView
//        // and determining when to reuse item views that are no longer visible to the user.
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // it initializes the NewsAdapter with an empty list of news articles and a lambda function that handles item clicks.
//        newsAdapter = NewsAdapter(newsList) { newsItem ->
//            //This lambda function is called when a news item is clicked.
//            Toast.makeText(this, "Clicked: ${newsItem.title}", Toast.LENGTH_SHORT).show()
//        }
//        //sets the adapter for the recycler view to the newsAdapter instance created earlier.
//        //This means that RecyclerView will use newsAdapter to display the list of news.
//        recyclerView.adapter = newsAdapter
//
//        // Fetch data from API
//        fetchNews()
//    }



//    //This method fetches news data from the API using the NewsRepository class.
//    private fun fetchNews() {
//
//        //progressBar is set to visible to indicate that the data is being fetched.
//        progressBar.visibility = View.VISIBLE
//        //The NewsRepository class is instantiated
//        val repository = NewsRepository()
//        //The fetchNews method is called on the repository instance to fetch news data from the API.
//        //The enqueue method is used to asynchronously execute the network request and handle the response.
//        //
//        repository.fetchNews().enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                progressBar.visibility = View.GONE
//
//                if (response.isSuccessful) {
//                    val fetchedNews = response.body()?.articles ?: emptyList()
//                    newsList.clear()
//                    newsList.addAll(fetchedNews)
//                    //The newsList is updated with the fetched news data, and the adapter is notified of the data change.
//                    //This triggers the RecyclerView to update and display the new data.
//                    newsAdapter.notifyDataSetChanged()
//                } else {
//                    Toast.makeText(this@MainActivity, "API Error!", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                progressBar.visibility = View.GONE
//                Toast.makeText(this@MainActivity, "Failed to connect to the server.", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//}
