package com.example.yurich.tuturutest.departure

import com.arellomobile.mvp.InjectViewState
import com.example.yurich.tuturutest.SchedulePresenter
import com.example.yurich.tuturutest.network.City
import com.example.yurich.tuturutest.network.TutuServerApi
import com.example.yurich.tuturutest.network.TutuStationsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by yurich on 15.12.16.
 */
@InjectViewState
class DeparturePresenter : SchedulePresenter() {

    var server = TutuStationsApi(
            Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/tutu-ru/hire_android_test/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TutuServerApi::class.java)
    )

    override fun retrieveAndShow() {

        val observable = Observable.create<List<City>> {
            subscriber ->

            val call = server.getStationsList()
            val response = call.execute()

            if (response.isSuccessful) {
                val citiesFrom = response.body().citiesFrom

                subscriber.onNext(citiesFrom)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { viewState.setData(it) },
                        { viewState.displayError(it) }
                )
    }
}