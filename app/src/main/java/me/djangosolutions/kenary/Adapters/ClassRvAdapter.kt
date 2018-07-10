package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.Entity.Class
import me.djangosolutions.kenary.R

class ClassRvAdapter(private val mcontext: Context) : RecyclerView.Adapter<ClassRvAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null
    private var mClass: List<Class>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(mcontext)
        val view = inflater!!.inflate(R.layout.cat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameclass: TextView = holder.nameclass
        val imgclass: ImageView = holder.imgclass
        nameclass.text = mClass!![position].title
        imgclass.setImageResource(R.drawable.ic_dashboard_black_24dp)
        holder.cardview.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = if (mClass == null) 0  else mClass!!.size

    fun setClassroom(classroom: List<me.djangosolutions.kenary.Entity.Class>){
        mClass = classroom
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cardview: CardView = itemView.findViewById(R.id.cardView)
        internal var nameclass: TextView = itemView.findViewById(R.id.catname)
        internal var imgclass: ImageView = itemView.findViewById(R.id.imagecat)
    }
}