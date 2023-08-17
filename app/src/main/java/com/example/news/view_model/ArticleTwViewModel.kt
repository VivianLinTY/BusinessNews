package com.example.news.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.news.repository.ArticleRepository
import com.example.news.response.ArticleResponse

class ArticleTwViewModel(application: Application) : AndroidViewModel(application) {
    val dashboardNewsResponseLiveData: LiveData<ArticleResponse>
    private val articleRepository: ArticleRepository = ArticleRepository()

    init {
        dashboardNewsResponseLiveData = articleRepository.getDashBoardNews("tw")
    }

    override fun onCleared() {
        super.onCleared()
        articleRepository.cancelRequests()
    }
}