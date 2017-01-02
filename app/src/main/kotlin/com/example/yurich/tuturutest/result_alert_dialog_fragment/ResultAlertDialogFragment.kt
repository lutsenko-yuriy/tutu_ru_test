package com.example.yurich.tuturutest.result_alert_dialog_fragment

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.schedule.MainActivity

/**
 * Created by yurich on 19.12.16.
 */
class ResultAlertDialogFragment(val query: ResultQuery) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(query.getResultMessage())
                .setPositiveButton(
                        "Именно так!"
                ) { dialog, buttonClicked ->
                    (activity as MainActivity).setDefaultFragment()
                }
        return builder.create()
    }
}