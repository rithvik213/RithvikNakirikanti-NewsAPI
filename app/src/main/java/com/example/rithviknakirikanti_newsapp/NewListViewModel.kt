import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rithviknakirikanti_newsapp.APIService
import com.example.rithviknakirikanti_newsapp.Article
import com.example.rithviknakirikanti_newsapp.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> = _articles // Ensure this line exists

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(APIService::class.java)

    init {
        fetchNewsArticles("business")
    }

    fun fetchNewsArticles(category: String) {
        val call = apiService.getHeadlines(category = category)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    _articles.value = response.body()?.articles
                } else {
                    Log.d("API Error", "Server Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("API Call Failure", "Call Failed: ${t.message}")
            }
        })
    }
}
