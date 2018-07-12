package me.djangosolutions.kenary.Fragments.PFragHome

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import me.djangosolutions.kenary.Adapters.ViewPagerAdapter
import me.djangosolutions.kenary.R

class PFragHomeContent: Fragment() {

    private val CLE = "LLAVE"
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(CLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_home_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tablayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewpager = view.findViewById<ViewPager>(R.id.viewPager)
        val adapter = ViewPagerAdapter(view.context, "type",childFragmentManager)
        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)
    }

    companion object {
        fun newInstance(type: String) =
                PFragHomeContent().apply {
                    arguments = Bundle().apply {
                        putString(CLE, type)
                    }
                }
    }
}