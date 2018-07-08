package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import me.djangosolutions.kenary.Fragments.FragClass
import me.djangosolutions.kenary.Fragments.FragTuto
import me.djangosolutions.kenary.R
import java.util.ArrayList

class ViewPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment = when (position) {
        0 -> FragClass()
        1 -> FragTuto()
        else -> FragClass()
    }

    override fun getCount(): Int =  2


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.title_class)
            1 -> context.getString(R.string.title_tutorias)
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}