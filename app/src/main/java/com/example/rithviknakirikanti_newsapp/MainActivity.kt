package com.example.rithviknakirikanti_newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/") // Ensure this is your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIService::class.java)

        // Here you call the API
        val call = apiService.getHeadlines(category = "business")

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    // This is your successful response
                    response.body()?.let { newsResponse ->
                        Log.d("test","Total Results: ${newsResponse.totalResults}")
                        newsResponse.articles.forEach { article ->
                            Log.d("test","Title: ${article.title}")
                        }
                    }
                } else {
                    // Handle the case where the server responds with an error
                    Log.d("test","Server Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                // This is called if there's a failure in executing the request
                Log.d("test","Call Failed: ${t.message}")
            }

        })

         */
    }
}