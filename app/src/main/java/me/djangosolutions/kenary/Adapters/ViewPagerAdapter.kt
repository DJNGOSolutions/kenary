package me.djangosolutions.kenary.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val listFragment = ArrayList<Fragment>()
    private val listTitle = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }


    override fun getPageTitle(position: Int): CharSequence? {

        return listTitle[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    fun setFragList(i: Int, hola: String, frag: Fragment) {
        listTitle[i] = hola
        listFragment[i] = frag
    }


}