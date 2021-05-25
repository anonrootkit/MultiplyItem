package com.example.multiplyitem.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.multiplyitem.home.HomeFragment

class ViewPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentsList = ArrayList<Fragment>().apply {
        add(HomeFragment())
        add(HomeFragment())
        add(HomeFragment())
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "First"
            1 -> "Second"
            else -> "third"
        }
    }

    override fun getCount(): Int {
        return fragmentsList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentsList[position]
    }

}