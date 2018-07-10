package me.djangosolutions.kenary.Firebase.Model.RecyclerView.item

import android.content.Context
import android.os.Message
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import me.djangosolutions.kenary.Firebase.Model.TextMessage
import me.djangosolutions.kenary.R

class TextMessageItem(val message: TextMessage,
                      val context: Context)
    : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        //TODO: Placeholder bind
    }

    override fun getLayout() = R.layout.item_text_message
}