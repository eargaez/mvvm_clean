package com.eargaez.mvvm_clean.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Project: RedBe
 * <p>
 * User: Eduardo
 * Date: 28/05/20
 * <p>
 * Created with IntelliJ IDEA
 */
class ViewPagerAdapter(
    private var fragments: ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}