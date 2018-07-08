package me.djangosolutions.kenary.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.Adapters.ClassRvAdapter
import me.djangosolutions.kenary.Adapters.NotiRvAdapter
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.Models.ModelNoti
import me.djangosolutions.kenary.R
import java.util.ArrayList

class FragNoti : Fragment() {
    private var recyclerView: RecyclerView? = null
    var list: List<ModelNoti>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.notificationrv)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        val adapter = NotiRvAdapter(view.context, getList())
        recyclerView!!.adapter = adapter
    }

    fun getList(): ArrayList<ModelNoti> {
        val list = ArrayList<ModelNoti>()
        list.add(ModelNoti("Tu solicitud de tutoria para Ciencias de los Materiales ha sido aceptada", "16 jun"))
        list.add(ModelNoti("Tu solicitud de tutoria para Calculo ha sido aceptada", "16 jun"))
        list.add(ModelNoti("Has sido inscrito correctamente al ClassRoom de Contaduria", "16 jun"))
        list.add(ModelNoti("Hola bienvenido a Kenary", "16 jun"))
        return list
    }
}