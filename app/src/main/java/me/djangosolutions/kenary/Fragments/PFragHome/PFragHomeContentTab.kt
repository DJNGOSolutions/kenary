package me.djangosolutions.kenary.Fragments.PFragHome

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.djangosolutions.kenary.Adapters.ClassItemRvAdapter
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
    private var listener: OnPFragHomeContentTabInteractionListener? = null
    private val classroomkey = "classroom"
    private val tutoriakey = "tutorias"
    private val classroomitemkey = "classroomitem"
    private val tutorialitemkey = "tutorialitem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(CLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main_home_content_tabfab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mClassViewModel = ViewModelProviders.of(this).get(ClassViewModel::class.java)
        mTutorialViewModel = ViewModelProviders.of(this).get(TutorialViewModel::class.java)
        recyclerView = view.findViewById(R.id.classroomrvfab)
        val lManager = when (type){
            classroomkey -> {GridLayoutManager(this.context, 2)}
            tutoriakey -> {LinearLayoutManager(this.context)}
            classroomitemkey -> {LinearLayoutManager(this.context)}
            tutorialitemkey -> {LinearLayoutManager(this.context)}
            else -> {LinearLayoutManager(this.context)}
        }
        val butty = view.findViewById<FloatingActionButton>(R.id.buttonadd)
        if (type == classroomkey) butty.hide()
        recyclerView!!.layoutManager = lManager
         when(type){
            classroomkey -> {val adapter = ClassRvAdapter(view.context, listener)
                mClassViewModel!!.getAll().observe(this, Observer<List<Class>>{ t -> adapter.setClassroom(t!!)})
                recyclerView!!.adapter = adapter
            }
            tutoriakey -> {val adapter = TutoRvAdapter(view.context)
                mTutorialViewModel!!.getAll().observe(this, Observer<List<Tutorial>>{ t -> adapter.setTutorial(t!!) })
                recyclerView!!.adapter = adapter
            }
           classroomitemkey-> {val adapter = ClassItemRvAdapter(view.context)
               mClassViewModel!!.getAll().observe(this, Observer<List<Class>>{ t ->  adapter.setClassroomI(t!!)})
               recyclerView!!.adapter = adapter
           }
        }
    }

    fun onCardviewPressed(type: String){
        listener?.onFragmentInteraction(type)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnPFragHomeContentTabInteractionListener){
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnPFragHomeContentTabInteractionListener {
        fun onFragmentInteraction(type: String)
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