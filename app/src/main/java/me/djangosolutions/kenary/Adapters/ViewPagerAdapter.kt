package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import me.djangosolutions.kenary.Fragments.PFragHomeContentTab
import me.djangosolutions.kenary.R

class ViewPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment = when (position) {
        0 -> PFragHomeContentTab.newInstance("classroom")
        1 -> PFragHomeContentTab.newInstance("tutorias")
        else -> PFragHomeContentTab()
    }

    override fun getCount(): Int =  2


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.title_class)
            1 -> context.getString(R.string.title_tuto)
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}