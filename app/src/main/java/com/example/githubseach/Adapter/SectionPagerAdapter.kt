package com.example.githubusersearch.main.Adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubseach.R
import com.example.githubusersearch.main.Fragment.FollowerFragment
import com.example.githubusersearch.main.Fragment.FollowingFragment

class SectionPagerAdapter(private val nCtx: Context, fn: FragmentManager, data: Bundle): FragmentPagerAdapter(fn, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle
    init {

        fragmentBundle = data
    }
    @StringRes
    private  val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2)
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return nCtx.resources.getString(TAB_TITLES[position])
    }
}