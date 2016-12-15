package com.example.yurich.tuturutest.ui

import android.widget.TextView
import com.airbnb.epoxy.EpoxyModel
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.network.City

/**
 * Created by yurich on 15.12.16.
 */
class HeaderModel(val city: City) : EpoxyModel<TextView>() {

    @android.support.annotation.LayoutRes
    override fun getDefaultLayout(): Int {
        return R.layout.item_header
    }

    override fun bind(view: TextView?) {
        view?.text = city.getAddress()
    }

}