package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import me.djangosolutions.kenary.Entity.Class
import me.djangosolutions.kenary.R

class ClassItemRvAdapter(private val mcontext: Context) : RecyclerView.Adapter<ClassItemRvAdapter.ViewHolder>() {

    private var mClassI: List<Class>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.class_item, parent, false))
    }

    override fun getItemCount(): Int = if (mClassI == null) 0 else mClassI!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameclassi.text = mClassI!![position].title
        holder.tutorclassi.text = mClassI!![position].count.toString()
    }

    fun setClassroomI(classroomI: List<Class>){
        mClassI = classroomI
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        internal var nameclassi: TextView = itemView.findViewById(R.id.numberclass)
        internal var tutorclassi: TextView = itemView.findViewById(R.id.nameclass)
        internal var imgroundclass: CircleImageView = itemView.findViewById(R.id.imageclass)
    }
}