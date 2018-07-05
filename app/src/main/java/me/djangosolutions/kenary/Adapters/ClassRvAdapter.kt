package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.R

class ClassRvAdapter(private val mcontext: Context, var modelClassList: List<ModelClass>) : RecyclerView.Adapter<ClassRvAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(mcontext)
        val view = inflater!!.inflate(R.layout.class_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameclass: TextView
        val numberclass: TextView
        val imgclass: ImageView

        nameclass = holder.nameclass
        numberclass = holder.numberclass
        imgclass = holder.imgclass

        numberclass.setText(modelClassList[position].number)
        nameclass.setText(modelClassList[position].name)
        imgclass.setImageResource(R.drawable.ic_dashboard_black_24dp)
    }

    override fun getItemCount(): Int {
        return if (modelClassList.isEmpty()) {
            0
        } else modelClassList.size

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var numberclass: TextView
        internal var nameclass: TextView
        internal var imgclass: ImageView

        init {
            numberclass = itemView.findViewById(R.id.numberclass)
            nameclass = itemView.findViewById(R.id.nameclass)
            imgclass = itemView.findViewById(R.id.imageclass)
        }
    }
}