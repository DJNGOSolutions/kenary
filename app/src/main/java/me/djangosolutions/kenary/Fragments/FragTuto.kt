package me.djangosolutions.kenary.Fragments

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
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.Models.ModelTuto
import me.djangosolutions.kenary.R
import java.util.ArrayList

class FragTuto : Fragment() {
    private var v: View? = null
    private var recyclerView: RecyclerView? = null

    var list: List<ModelClass>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.tutorias, container, false)




        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = v!!.findViewById(R.id.tutoriasrv)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        val adapter = TutoRvAdapter(view.context, getList())
        recyclerView!!.adapter = adapter
    }

    fun getList(): ArrayList<ModelTuto> {
        //Aqui deberia ir la funcion que obtendra la lista de class rooms
        val list = ArrayList<ModelTuto>()
        list.add(ModelTuto("1", "Derivadas"))
        list.add(ModelTuto("2", "Ecuaciones Diferenciales"))
        list.add(ModelTuto("3", "Economía"))
        return list
    }
}