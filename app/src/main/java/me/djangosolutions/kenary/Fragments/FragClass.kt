package me.djangosolutions.kenary.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.Adapters.ClassRvAdapter
import java.util.ArrayList
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.R


class FragClass : Fragment() {
    private var v: View? = null
    private var recyclerView: RecyclerView? = null
    var list: List<ModelClass>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.classrooms, container, false)
        recyclerView = v!!.findViewById(R.id.classroomrv)

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        val adapter = ClassRvAdapter(context, getList())
        recyclerView!!.adapter = adapter

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun getList(): List<ModelClass> {
        //Aqui deberia ir la funcion que obtendra la lista de class rooms
        val list = ArrayList<ModelClass>()
        list.add(ModelClass("1", "7 Classrooms", "Fluídos"))
        list.add(ModelClass("2", "10 Classrooms", "Cálculo"))
        list.add(ModelClass("3", "2 Classrooms", "Ciencias de los materiales"))
        list.add(ModelClass("4", "5 Classrooms", "Contaduría"))
        return list
    }
}