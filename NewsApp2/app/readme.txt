model/ → Handles data representation & API response.
view/ → Contains Activities that control UI.
controller/ → Handles logic like RecyclerView Adapter.
ui/ → Contains XML files for UI design.

Dependencies
Why Do We Need These?
Retrofit → Makes network requests to fetch news data.
Gson Converter → Converts JSON responses into Kotlin objects.
RecyclerView → Displays a list of news articles.
Glide → Loads images efficiently.
Coroutines → Handles background tasks (like API calls).

Thought process ->
activity_main.xml (News List Screen)
Thought Process:
This is the home screen of the app.
It should show a list of news articles.
We use RecyclerView for better performance when handling lists.

Why RecyclerView instead of ListView?
Better performance (only creates limited views, not all at once).
More flexible (supports animations, multiple view types).

item_news.xml (Single News Item)
Thought Process:

Each news item should have:
An image (to show the news thumbnail).
A title (to give a brief idea of the news).
It needs to be repeated for each news article inside RecyclerView.

activity_news_detail.xml (News Detail Screen)
Thought Process:
This screen should display complete details about a news article.
We need:
A large image at the top.
A title for the news.
A detailed description.

NewsAdapter.kt (RecyclerView Adapter)
Thought Process

We need a list of news items (passed as a parameter).
Each item needs a ViewHolder to represent a single news article.
We use Glide to load images from the internet.


API SERVICE
Thought Process:
ApiService is an interface that defines API endpoints using Retrofit.
It is responsible for defining how requests are made, but it does not execute them.
How It Works
@GET("top-headlines") → Defines which endpoint to call.
@Query("country") → Adds parameters (country=us).
Returns Call<NewsResponse> → Retrofit uses this to fetch data.
🛠 ApiService is just a definition!

It does not make API calls itself.
The actual call happens in NewsRepository.


NewsRepository.kt (Fetches News Data)
Thought Process:
NewsRepository is a class that fetches data from the API using ApiService.
It creates Retrofit and makes API calls.
Retrofit is created with baseUrl("https://newsapi.org/v2/").
Retrofit converts API responses into NewsResponse objects.
Creates apiService instance from ApiService.
Calls getNews() method to get API data.

How Data Flows in the App (LEGACY)
----------------------------------------------------------------------------------------
UI (MainActivity)  --->  Repository (fetches data)  --->  API Service (calls API)
        ↓                                ↓
 RecyclerView                       Retrofit gets data
        ↓                                ↓
 Adapter Updates UI             Returns JSON response
----------------------------------------------------------------------------------------
Example Flow ::
User opens MainActivity → Calls fetchNews().
MainActivity calls NewsRepository.fetchNews().
NewsRepository calls ApiService.getNews().
Retrofit fetches JSON from the API.
API returns NewsResponse → Updates UI.