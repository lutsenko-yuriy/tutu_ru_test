package com.example.yurich.tuturutest.splash

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.utils.fromLocalDb
import com.example.yurich.tuturutest.utils.setDbUpdated
import javax.inject.Inject

/**
 * Created by yurich on 02.01.17.
 */
class RetainFragment: Fragment(), Repository.LoadingProgressListener {

    @Inject
    lateinit var repository: Repository

    companion object {
        val STATE_LOADING = 0
        val STATE_ERROR = 1
    }

    init{
        SplashActivity.subcomponent!!.inject(this)
    }

    var currentState = STATE_LOADING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        Log.v("REPOSITORY", "Splash Activity")

        loadData()
    }

    fun loadData() {
        val fromLocalDb = activity.fromLocalDb()
        repository.refreshCachedStations(fromLocalDb, this)
    }

    override fun onDone() {
        (activity as SplashActivity).onDone()
    }

    override fun onError() {
        (activity as SplashActivity).displayError()
    }
}