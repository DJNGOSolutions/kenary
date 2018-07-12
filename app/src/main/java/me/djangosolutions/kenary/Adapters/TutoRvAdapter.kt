package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.Entity.Tutorial
import me.djangosolutions.kenary.Entity.TutorialEntity
import me.djangosolutions.kenary.R

class TutoRvAdapter(private val mcontext: Context) : RecyclerView.Adapter<TutoRvAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null
    private var mTutorial: List<TutorialEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(mcontext)
        val view = inflater!!.inflate(R.layout.tuto_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nametuto: TextView = holder.nametuto
        val imgtuto: ImageView = holder.imgtuto
        nametuto.text = mTutorial!![position].TutorialTheme
        imgtuto.setImageResource(R.drawable.ic_dashboard_black_24dp)
    }

    override fun getItemCount(): Int = if (mTutorial == null) 0 else mTutorial!!.size

    fun setTutorial(tutorial: List<TutorialEntity>){
        mTutorial = tutorial
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var nametuto: TextView = itemView.findViewById(R.id.nametuto)
        internal var imgtuto: ImageView = itemView.findViewById(R.id.imgtuto)
    }
}