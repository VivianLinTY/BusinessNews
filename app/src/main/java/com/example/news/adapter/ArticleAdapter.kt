package com.example.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Article

class ArticleAdapter(private val context: Context, articleArrayList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    var articleArrayList: ArrayList<Article>

    init {
        this.articleArrayList = articleArrayList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_each_article, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val article: Article = articleArrayList[i]
        viewHolder.tvTitle.setText(article.title)
        Glide.with(context)
            .load(article.urlToImage)
            .into(viewHolder.imgViewCover)
    }

    override fun getItemCount(): Int {
        return articleArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgViewCover: ImageView
        val tvTitle: TextView

        init {
            imgViewCover = itemView.findViewById(R.id.imgViewCover)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }
}