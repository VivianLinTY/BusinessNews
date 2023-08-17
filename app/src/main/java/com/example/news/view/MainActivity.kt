package com.example.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.news.adapter.ArticlePagerAdapter
import com.example.news.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
    }

    private fun init() {
        val tabLayout: TabLayout? = binding?.tabLayout
        val viewPager: ViewPager? = binding?.viewPager
        val pagerAdapter = ArticlePagerAdapter(supportFragmentManager)
        viewPager?.adapter = pagerAdapter
        tabLayout?.setupWithViewPager(viewPager)
    }
}