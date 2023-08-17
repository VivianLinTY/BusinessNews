package com.example.news.response

import com.example.news.model.Article
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleResponse {
    @SerializedName("articles")
    @Expose
    private var articles: List<Article>? = null
    fun getArticles(): List<Article>? {
        return articles
    }

    fun setArticles(articles: List<Article>?) {
        this.articles = articles
    }

    override fun toString(): String {
        return "BashboardNewsResponse{" +
                "articles=" + articles +
                '}'
    }
}