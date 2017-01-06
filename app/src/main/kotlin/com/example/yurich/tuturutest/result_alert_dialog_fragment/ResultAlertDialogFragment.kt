package com.example.yurich.tuturutest.result_alert_dialog_fragment

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.repository.ResultQuery

/**
 * Created by yurich on 19.12.16.
 */
class ResultAlertDialogFragment() : DialogFragment() {

    var query: ResultQuery? = null

    fun newInstance(query: ResultQuery): ResultAlertDialogFragment {
        val fragment = ResultAlertDialogFragment()
        val bundle = Bundle()
        bundle.putSerializable("query", query)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        query = arguments.getSerializable("query") as ResultQuery
        builder.setMessage(query!!.getResultMessage())
                .setPositiveButton(getString(R.string.exactly)) {
                    dialog, buttonClicked ->
                    query!!.resetQuery()
                }
        return builder.create()
    }
}