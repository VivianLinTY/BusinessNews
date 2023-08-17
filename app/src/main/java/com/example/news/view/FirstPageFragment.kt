package com.example.news.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapter.ArticleAdapter
import com.example.news.databinding.FragmentFirstPageBinding
import com.example.news.model.Article
import com.example.news.repository.ArticleRepository
import com.example.news.response.ArticleResponse
import com.example.news.view_model.ArticleTwViewModel

class FirstPageFragment : Fragment() {
    private val articleArrayList: ArrayList<Article> = ArrayList()
    private var adapter: ArticleAdapter? = null
    private var articleViewModel: ArticleTwViewModel? = null
    private lateinit var binding: FragmentFirstPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        getArticles()
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = ArticleAdapter(requireContext(), articleArrayList)
        recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        val application = context?.applicationContext as Application
        articleViewModel = ArticleTwViewModel(application, ArticleRepository())
    }

    private fun getArticles() {
        articleViewModel!!.dashboardNewsResponseLiveData.observe(
            viewLifecycleOwner
        ) { articleResponse: ArticleResponse? ->
            if (articleResponse?.getArticles() != null && articleResponse.getArticles()!!
                    .isNotEmpty()
            ) {
                binding.progressBar.visibility = View.GONE
                val articleList = articleResponse.getArticles()
                articleArrayList.addAll(articleList!!)
                adapter?.notifyDataSetChanged()
            }
        }
    }
}