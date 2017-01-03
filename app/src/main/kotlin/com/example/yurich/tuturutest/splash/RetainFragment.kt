package com.example.yurich.tuturutest.splash

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.utils.fromLocalDb
import com.example.yurich.tuturutest.utils.isDbReseted
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by yurich on 02.01.17.
 */
class RetainFragment: Fragment() {

    @Inject
    lateinit var repository: Repository

    companion object {
        val STATE_LOADING = 0
        val STATE_ERROR = 1
    }

    init {
        SplashActivity.subcomponent.inject(this)
    }

    var currentState = STATE_LOADING

    var stationsAmount = 0

    val subscriptions = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        Log.v("REPOSITORY", "Splash Activity")

        loadData()
    }

    fun loadData() {
        val fromLocalDb = activity.fromLocalDb()
        if (fromLocalDb) {
            changeActivity()
        } else {
            subscriptions.add(repository.getStations(fromLocalDb)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                stationsAmount++
                                (activity as SplashActivity).displayProgress(stationsAmount)
                            },
                            {
                                (activity as SplashActivity).displayError()
                                currentState = STATE_ERROR
                            },
                            {
                                activity.isDbReseted(true)
                                changeActivity()
                            }
                    ))
        }
    }

    private fun changeActivity() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    override fun onDestroy() {
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        super.onDestroy()
    }
}