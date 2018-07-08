package me.djangosolutions.kenary.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.Models.ModelNoti
import me.djangosolutions.kenary.Models.ModelTuto
import me.djangosolutions.kenary.R

class NotiRvAdapter(private val mcontext: Context, var modelNotiList: List<ModelNoti>) : RecyclerView.Adapter<NotiRvAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(mcontext)
        val view = inflater!!.inflate(R.layout.noti_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datenoti: TextView
        val noti: TextView
        val imgnoti: ImageView

        datenoti = holder.datenoti
        noti = holder.noti
        imgnoti = holder.imgnoti

        noti.setText(modelNotiList[position].notifi)
        datenoti.setText(modelNotiList[position].date)
        imgnoti.setImageResource(R.drawable.ic_dashboard_black_24dp)
    }

    override fun getItemCount(): Int {
        return if (modelNotiList.isEmpty()) {
            0
        } else modelNotiList.size

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var datenoti: TextView
        internal var noti: TextView
        internal var imgnoti: ImageView

        init {
            datenoti = itemView.findViewById(R.id.datenoti)
            noti = itemView.findViewById(R.id.notifications)
            imgnoti = itemView.findViewById(R.id.imagenoti)
        }
    }
}
