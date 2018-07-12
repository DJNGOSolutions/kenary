package me.djangosolutions.kenary.Fragments.Dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import kotlinx.android.synthetic.main.edit_profile.*
import kotlinx.android.synthetic.main.profile.*
import me.djangosolutions.kenary.Firebase.Model.Utils.FirebaseUtil
import me.djangosolutions.kenary.Firebase.Model.Utils.StorageUtil
import me.djangosolutions.kenary.R
import org.jetbrains.anko.support.v4.toast
import java.io.ByteArrayOutputStream

class FragDialogModifUser: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.edit_profile, container, false)

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
}