package com.example.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.news.constants.AppConstant
import com.example.news.view.FirstPageFragment
import com.example.news.view.SecondPageFragment

class ArticlePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (position == AppConstant.TabType.TAB_TW.ordinal) {
            FirstPageFragment()
        } else {
            SecondPageFragment()
        }
    }

    override fun getCount(): Int {
        return AppConstant.TabType.values().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == AppConstant.TabType.TAB_TW.ordinal) {
            "TW"
        } else {
            "US"
        }
    }
}