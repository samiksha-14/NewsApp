//Detail Screen Controller
package com.example.newsapp.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newsapp.R

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail) // Load UI

        //Get referece to UI elements
        val newsTitle: TextView = findViewById(R.id.detailTitle)
        val newsDescription: TextView = findViewById(R.id.detailContent)
        val newsImage: ImageView = findViewById(R.id.detailImage)

        //Receive data from Intent
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("image")

        //Set data to UI elements
        newsTitle.text = title ?: "No title available"
        newsDescription.text = description ?: "No description available"
        newsImage.apply {
            Glide.with(this).load(imageUrl).into(this)


        }
    }
}
