package me.djangosolutions.kenary.Fragments.PFragHome

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.Adapters.ClassRvAdapter
import me.djangosolutions.kenary.Adapters.TutoRvAdapter
import me.djangosolutions.kenary.Entity.Tutorial
import me.djangosolutions.kenary.Entity.Class
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.Viewmodels.ClassViewModel
import me.djangosolutions.kenary.Viewmodels.TutorialViewModel


class PFragHomeContentTab : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var mClassViewModel: ClassViewModel? = null
    private var mTutorialViewModel: TutorialViewModel? = null
    private var CLE = "LLAVE"
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(CLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_home_content_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mClassViewModel = ViewModelProviders.of(this).get(ClassViewModel::class.java)
        mTutorialViewModel = ViewModelProviders.of(this).get(TutorialViewModel::class.java)
        recyclerView = view.findViewById(R.id.classroomrv)
        val lManager = when (type){
            "classroom" -> {GridLayoutManager(this.context, 2)}
            "tutorias" -> {LinearLayoutManager(this.context)}
            else -> {LinearLayoutManager(this.context)}
        }
        recyclerView!!.layoutManager = lManager
         when(type){
            "classroom" -> {val adapter = ClassRvAdapter(view.context)
                mClassViewModel!!.getAll().observe(this, Observer<List<Class>>{ t -> adapter.setClassroom(t!!)})
                recyclerView!!.adapter = adapter
            }
            "tutorias" -> {val adapter = TutoRvAdapter(view.context)
                mTutorialViewModel!!.getAll().observe(this, Observer<List<Tutorial>>{ t -> adapter.setTutorial(t!!) })
                recyclerView!!.adapter = adapter
            }
        }
    }

    companion object {
        fun newInstance(type: String) =
                PFragHomeContentTab().apply {
                    arguments = Bundle().apply {
                        putString(CLE, type)
                    }
                }
    }
}