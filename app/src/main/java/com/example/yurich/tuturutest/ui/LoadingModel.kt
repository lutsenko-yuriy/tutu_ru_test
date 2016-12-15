package com.example.yurich.tuturutest.ui

import android.support.annotation.LayoutRes
import android.widget.ProgressBar
import com.airbnb.epoxy.EpoxyModel
import com.example.yurich.tuturutest.R

/**
 * Created by yurich on 15.12.16.
 */
class LoadingModel : EpoxyModel<ProgressBar>() {
    @LayoutRes
    override fun getDefaultLayout(): Int {
        return R.layout.item_loading
    }
}