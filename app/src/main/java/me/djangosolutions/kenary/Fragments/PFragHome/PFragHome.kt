package me.djangosolutions.kenary.Fragments.PFragHome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.R

class PFragHome: Fragment() {

    private val CLE = "LLAVE"
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(CLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (type == "contents"){
            replaceFragment(PFragHomeContent())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frame_main_home, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance(type: String) =
                PFragHome().apply {
                    arguments = Bundle().apply {
                        putString(CLE, type)
                    }
                }
    }
}