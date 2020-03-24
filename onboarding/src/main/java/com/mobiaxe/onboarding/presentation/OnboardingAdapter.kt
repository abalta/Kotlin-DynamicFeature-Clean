package com.mobiaxe.onboarding.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(context: FragmentActivity) : FragmentStateAdapter(context) {

    private var fragmentList = mutableListOf<Fragment>()


    fun getFragmentList() = fragmentList

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}