package me.djangosolutions.kenary.Fragments

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.Adapters.ClassRvAdapter
import me.djangosolutions.kenary.Adapters.TutoRvAdapter
import me.djangosolutions.kenary.Entity.Tutorial
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.Models.ModelTuto
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.Viewmodels.TutorialViewModel
import java.util.ArrayList

class FragTuto : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var mTutorialViewModel: TutorialViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tutorias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTutorialViewModel = ViewModelProviders.of(this).get(TutorialViewModel::class.java)
        recyclerView = view.findViewById(R.id.tutoriasrv)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        val adapter = TutoRvAdapter(view.context)
        mTutorialViewModel!!.getAll().observe(this, Observer<List<Tutorial>>{ t -> adapter.setTutorial(t!!) })
        recyclerView!!.adapter = adapter
    }
}