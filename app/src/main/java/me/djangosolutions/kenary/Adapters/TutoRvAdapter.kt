package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.Models.ModelTuto

class TutoRvAdapter(private val mcontext: Context, var modelTutoList: List<ModelTuto>) : RecyclerView.Adapter<TutoRvAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(mcontext)
        val view = inflater!!.inflate(R.layout.tuto_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nametuto: TextView
        val imgtuto: ImageView
        nametuto = holder.nametuto
        imgtuto = holder.imgtuto
        nametuto.setText(modelTutoList[position].name)
        imgtuto.setImageResource(R.drawable.ic_dashboard_black_24dp)
    }

    override fun getItemCount(): Int {
        return if (modelTutoList.isEmpty()) {
            0
        } else modelTutoList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var nametuto: TextView
        internal var imgtuto: ImageView

        init {
            nametuto = itemView.findViewById(R.id.nametuto)
            imgtuto = itemView.findViewById(R.id.imgtuto)
        }
    }
}