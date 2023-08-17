package com.example.news.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article {
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    override fun toString(): String {
        return "BashboardNews{" +
                "urlToImage='" + urlToImage + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}'
    }
}