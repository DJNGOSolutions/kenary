package me.djangosolutions.kenary.Firebase.Model.RecyclerView.item

import android.content.Context
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_person.*
import me.djangosolutions.kenary.Firebase.Model.UserM
import me.djangosolutions.kenary.Firebase.Model.Utils.StorageUtil
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.glide.GlideApp


class PersonItem(val person: UserM,
                 val userId: String,
                 private val context: Context)
    : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_name.text = person.diplayName
        viewHolder.textView_bio.text = person.email
        if (person.profilePicPath != null)
            GlideApp.with(context)
                    .load(StorageUtil.pathToReference(person.profilePicPath))
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .into(viewHolder.imageView_profile_picture)
    }

    override fun getLayout() = R.layout.item_person
}