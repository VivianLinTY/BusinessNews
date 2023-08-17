package com.example.news.retrofit

import com.example.news.response.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Call<ArticleResponse?>?
}