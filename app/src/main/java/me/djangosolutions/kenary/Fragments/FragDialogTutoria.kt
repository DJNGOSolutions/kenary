package me.djangosolutions.kenary.Fragments

import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import me.djangosolutions.kenary.R


class FragDialogTutoria: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tuto_info, container, false)
    }

    /** The system calls this only when creating the layout in a dialog.  */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}