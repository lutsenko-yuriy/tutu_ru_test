package com.example.yurich.tuturutest

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import com.example.yurich.tuturutest.schedule.MainActivity

/**
 * Created by yurich on 05.01.17.
 */
class RefreshDialogFragment() : DialogFragment() {

    interface OnButtonClickListener {
        fun onConfirmRefresh()
        fun onDenyRefresh()
    }

    var listener: OnButtonClickListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(getString(R.string.refresh_question))
                .setPositiveButton(getString(R.string.confirm)) {
                    dialog, buttonClicked -> listener!!.onConfirmRefresh()
                }
                .setNegativeButton(getString(R.string.deny)) {
                    dialog, buttonClicked -> listener!!.onDenyRefresh()
                }
        return builder.create()
    }
}