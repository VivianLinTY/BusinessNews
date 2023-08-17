package com.example.news.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.constants.AppConstant.API_KEY
import com.example.news.response.ArticleResponse
import com.example.news.retrofit.ApiRequest
import com.example.news.retrofit.RetrofitRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository {
    private val apiRequest: ApiRequest =
        RetrofitRequest.retrofitInstance!!.create(ApiRequest::class.java)
    private var currentCall // Keep track of the current Call
            : Call<ArticleResponse?>? = null

    fun getDashBoardNews(country: String): LiveData<ArticleResponse> {
        val data: MutableLiveData<ArticleResponse> = MutableLiveData<ArticleResponse>()
        // Store the current Call instance
        Log.d("ArticleRepository", "getDashBoardNews country =$country")
        currentCall = apiRequest.getTopHeadlines(country, "business", API_KEY)
        currentCall!!.enqueue(object : Callback<ArticleResponse?> {
            override fun onResponse(
                call: Call<ArticleResponse?>,
                response: Response<ArticleResponse?>
            ) {
                Log.d(
                    TAG,
                    "onResponse response:: $response"
                )
                if (response.body() != null) {
                    data.setValue(response.body())
                    Log.d(TAG, "articles total result:: " + response.body())
                    Log.d(TAG, "size:: " + response.body()!!.getArticles()?.size)
                }
            }

            override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                data.setValue(null)
            }
        })
        return data
    }

    // Method to cancel the current request
    fun cancelRequests() {
        if (currentCall != null && !currentCall!!.isCanceled) {
            currentCall!!.cancel()
        }
    }

    companion object {
        private val TAG = ArticleRepository::class.java.simpleName
    }
}