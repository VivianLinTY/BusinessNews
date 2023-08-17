package com.example.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapter.ArticleAdapter
import com.example.news.databinding.FragmentSecondPageBinding
import com.example.news.model.Article
import com.example.news.response.ArticleResponse
import com.example.news.view_model.ArticleUsViewModel

class SecondPageFragment : Fragment() {
    private val articleArrayList: ArrayList<Article> = ArrayList()
    private var adapter: ArticleAdapter? = null
    private var articleViewModel: ArticleUsViewModel? = null
    private lateinit var binding: FragmentSecondPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondPageBinding.inflate(inflater, container, false)
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
        articleViewModel = ViewModelProvider(this).get(ArticleUsViewModel::class.java)
    }

    private fun getArticles() {
        articleViewModel!!.dashboardNewsResponseLiveData.observe(
            viewLifecycleOwner
        ) { articleResponse: ArticleResponse? ->
            if (articleResponse?.getArticles() != null && articleResponse.getArticles()!!.isNotEmpty()
            ) {
                binding.progressBar.visibility = View.GONE
                val articleList = articleResponse.getArticles()
                articleArrayList.addAll(articleList!!)
                adapter?.notifyDataSetChanged()
            }
        }
    }
}