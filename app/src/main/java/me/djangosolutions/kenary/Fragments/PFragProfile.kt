package me.djangosolutions.kenary.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.Adapters.TutoRvAdapter
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHome
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHomeContent
import me.djangosolutions.kenary.R

class PFragProfile : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(PFragHomeContent())
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frame_profile, fragment)
        fragmentTransaction.commit()
    }
}