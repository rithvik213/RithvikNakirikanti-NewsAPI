package com.example.rithviknakirikanti_newsapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("v2/top-headlines")
    fun getHeadlines(
        @Query("country")
        countryCode:String = "us",
        @Query("category")
        category: String?,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = "d045d44200bd47e89a3c1c8046ad787d"
    ): Call<News>
}