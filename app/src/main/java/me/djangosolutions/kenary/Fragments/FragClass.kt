package me.djangosolutions.kenary.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.Adapters.ClassRvAdapter
import me.djangosolutions.kenary.Entity.Class
import java.util.ArrayList
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.Viewmodels.ClassViewModel


class FragClass : Fragment() {
    private var mClassViewModel: ClassViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.classrooms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mClassViewModel = ViewModelProviders.of(this).get(ClassViewModel::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.classroomrv)
        val rv = view.findViewById<RecyclerView>(R.id.classroomrv)
        val lManager = GridLayoutManager(this.context, 2)
        rv!!.layoutManager = lManager
        val adapter = ClassRvAdapter(view.context)
        mClassViewModel!!.getAll().observe(this, Observer<List<Class>>{ t -> adapter.setClassroom(t!!) })
        recyclerView.adapter = adapter
    }
}