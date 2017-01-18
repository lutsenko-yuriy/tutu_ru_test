package com.example.yurich.tuturutest

import com.squareup.leakcanary.LeakCanary

/**
 * Created by yurich on 13.01.17.
 */
class StationsAppDebug: StationsApp() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}