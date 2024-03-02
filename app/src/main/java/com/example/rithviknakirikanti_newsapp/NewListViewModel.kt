import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rithviknakirikanti_newsapp.APIService
import com.example.rithviknakirikanti_newsapp.Article
import com.example.rithviknakirikanti_newsapp.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListViewModel : ViewModel() {

    // LiveData for observing articles
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    // Retrofit setup (consider moving this to a repository class in a real app)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/") // Ensure this is your base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(APIService::class.java)

    // Function to fetch news articles based on category
    fun fetchNewsArticles(category: String) {
        val call = apiService.getHeadlines(category = category)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    _articles.value = response.body()?.articles
                } else {
                    // Handle API error response
                    Log.d("API Error", "Server Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                // Log or handle API call failure
                Log.d("API Call Failure", "Call Failed: ${t.message}")
            }
        })
    }
}
