package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.Entity.ClassroomEntity
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHomeContentTab
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.glide.GlideApp

class ClassRvAdapter(private val mcontext: Context, private val listener: PFragHomeContentTab.OnPFragHomeContentTabInteractionListener?) : RecyclerView.Adapter<ClassRvAdapter.ViewHolder>() {

    private val mListener: View.OnClickListener = View.OnClickListener { v ->
        val classroom = v.tag as ClassroomEntity
        listener?.onFragmentInteraction(classroom.CategoryName)
    }
    private var inflater: LayoutInflater? = null
    private var mClass: List<ClassroomEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(mcontext)
        val view = inflater!!.inflate(R.layout.cat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameclass.text = mClass!![position].CategoryName
        GlideApp.with(holder.itemView)
                .load(mClass!![position].ClassroomPhoto)
                .centerCrop()
                .placeholder(R.drawable.ic_dashboard_black_24dp)
                .into(holder.imgclass)
        holder.imgclass.setOnClickListener {
            mListener.onClick(holder.itemView)
        }
        with(holder.itemView){
            tag = mClass?.get(position)
            setOnClickListener { mListener }
        }
    }

    override fun getItemCount(): Int = if (mClass == null) 0  else mClass!!.size

    fun setClassroom(classroom: List<me.djangosolutions.kenary.Entity.ClassroomEntity>){
        mClass = classroom
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cardview: CardView = itemView.findViewById(R.id.cardView)
        internal var nameclass: TextView = itemView.findViewById(R.id.catname)
        internal var imgclass: ImageView = itemView.findViewById(R.id.imagecat)
    }
}