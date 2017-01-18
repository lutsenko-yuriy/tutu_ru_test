package com.example.yurich.tuturutest

import android.app.Application
import com.example.yurich.tuturutest.di.AppComponent
import com.example.yurich.tuturutest.di.DaggerAppComponent
import com.example.yurich.tuturutest.di.Modules.AppModule
import io.realm.Realm

/**
 * Created by yurich on 17.12.16.
 */
open class StationsApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    @SuppressWarnings("deprecated")
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        Realm.init(this)
    }
}