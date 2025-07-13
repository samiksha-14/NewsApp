//recycler view adapter
//The adapter binds the data to the UI.
//The adapter is responsible for creating the views that represent the data items
// and populating them with the data.

package com.example.newsapp.controller
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.News
import com.example.newsapp.view.NewsDetailActivity

class NewsAdapter(private var newsList: List<News>, private val onItemClick: (News) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

     //NewsViewHolder represents a single row in the RecyclerView.
     //It stores references to UI elements (TextView, ImageView).
     //If there are 10 news articles, then 10 NewsViewHolder objects will be created
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.newsTitle)
        val description: TextView = view.findViewById(R.id.newsDescription)
        val image: ImageView = view.findViewById(R.id.newsImage)
    }

    //This method creates the UI layout for a single item.
    //It inflates item_news.xml, which defines how each row should look.
    //If you have 100 articles, Android only creates 10-15 ViewHolders at a time.
    //As the user scrolls, RecyclerView reuses old ViewHolders (performance optimization).

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    //This method fills each row with actual data.
    //newsItem = newsList[position] → Retrieves the current news article.
    //.text = newsItem.title ?: "No Title" → Sets title & description.
    //Glide.with(...) → Loads the news image from URL.
//    If newsList[0] is:
//    News(title="Breaking News", description="Something important happened", urlToImage="https://example.com/image.jpg")
//    then onBindViewHolder() will:
//    Set title.text = "Breaking News".
//    Set description.text = "Something important happened".
//    Load urlToImage into ImageView.
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.title.text = newsItem.title ?: "No Title"
        holder.description.text = newsItem.description ?: "No Description"
        Glide.with(holder.itemView.context).load(newsItem.urlToImage).into(holder.image)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title", newsItem.title)
            intent.putExtra("description", newsItem.description)
            intent.putExtra("image", newsItem.urlToImage)
            context.startActivity(intent) //open newsDetalActivity

        }
    }

    //What is getItemCount()?
    //This function tells RecyclerView how many items exist.
    //Returns the total number of news articles.
    //If newsList has 50 articles, getItemCount() returns 50.
    //RecyclerView knows it needs to display 50 items.
    override fun getItemCount() = newsList.size


    //What is updateNews()?
    //Updates newsList with new articles.
    //Calls notifyDataSetChanged() → Refreshes RecyclerView UI.
    fun updateNews(newNews: List<News>) {
        newsList = newNews
        notifyDataSetChanged()
    }
}


//newsList: List<News>	Holds the data (list of news articles).
//NewsViewHolder	Represents a single row (one news item).
//onCreateViewHolder	Inflates item_news.xml for each list item.
//onBindViewHolder	Binds the news title and image to UI.
//Glide.with(...).load(...).into(...)	Loads the image from a URL into ImageView.
//onItemClick(newsItem)	Handles item clicks (navigates to Detail Screen).