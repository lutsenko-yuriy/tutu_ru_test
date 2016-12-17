package com.example.yurich.tuturutest.departure

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yurich.tuturutest.ScheduleView
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.RetrieveSpecification
import com.example.yurich.tuturutest.repository.RetrieveSpecification.SpecOptions.DEPARTURE
import com.example.yurich.tuturutest.repository.RetrieveSpecification.SpecOptions.FROM_SERVER
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.remote_storage.TutuServerApi
import com.example.yurich.tuturutest.repository.remote_storage.TutuStationsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by yurich on 15.12.16.
 */
@InjectViewState
class DeparturePresenter() : MvpPresenter<ScheduleView>() {

    var subscriptions = CompositeSubscription()

    @Inject
    lateinit var repository: Repository

    init {
        StationsApp.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun detachView(view: ScheduleView?) {
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        super.detachView(view)
    }

    fun retrieveAndShow() {
        subscriptions.add(
                repository.getStations(RetrieveSpecification(DEPARTURE, FROM_SERVER))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { viewState.updateStations(it) },
                                { viewState.displayError(it) }
                        )
        )
    }
}