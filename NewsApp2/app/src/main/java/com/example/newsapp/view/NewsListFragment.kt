package com.example.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.controller.NewsAdapter
import com.example.newsapp.model.News
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var noNewsText: TextView

    private val newsList = mutableListOf<News>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false) // Inflate layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        noNewsText = view.findViewById(R.id.noNewsText)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsAdapter = NewsAdapter(newsList) { newsItem ->
            Toast.makeText(requireContext(), "Clicked: ${newsItem.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = newsAdapter

        fetchNews()
    }

    private fun fetchNews() {
        progressBar.visibility = View.VISIBLE

        val repository = NewsRepository()
        repository.fetchNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val fetchedNews = response.body()?.articles ?: emptyList()

                    if (fetchedNews.isEmpty()) {
                        noNewsText.visibility = View.VISIBLE
                    } else {
                        noNewsText.visibility = View.GONE
                        newsList.clear()
                        newsList.addAll(fetchedNews)
                        newsAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "API Error!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Failed to connect to the server.", Toast.LENGTH_LONG).show()
            }
        })
    }
}
