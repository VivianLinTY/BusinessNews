package com.example.news.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.news.repository.ArticleRepository
import com.example.news.response.ArticleResponse

class ArticleTwViewModel(
    application: Application,
    private val articleRepository: ArticleRepository
) : AndroidViewModel(application) {
    val dashboardNewsResponseLiveData: LiveData<ArticleResponse> =
        articleRepository.getDashBoardNews("tw")

    public override fun onCleared() {
        super.onCleared()
        articleRepository.cancelRequests()
    }
}