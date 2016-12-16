package com.example.yurich.tuturutest.departure

import com.arellomobile.mvp.InjectViewState
import com.example.yurich.tuturutest.SchedulePresenter
import com.example.yurich.tuturutest.repository.database.StoragedCity
import com.example.yurich.tuturutest.repository.database.StoragedEntity
import com.example.yurich.tuturutest.repository.database.StoragedStation
import com.example.yurich.tuturutest.repository.network.TutuServerApi
import com.example.yurich.tuturutest.repository.network.TutuStationsApi
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

    // TODO - put it in model
    var server = TutuStationsApi(
            Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/tutu-ru/hire_android_test/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TutuServerApi::class.java)
    )

    override fun retrieveAndShow() {

        val observable = Observable.create<List<StoragedEntity>> {
            subscriber ->

            val serverCall = server.getStationsList()
            val serverResponse = serverCall.execute()

            // Transform server response to storaged entities
            // if we received the response successfully
            if (serverResponse.isSuccessful) {
                val entitiesFromServer = serverResponse.body().citiesFrom
                val storagedEntities: MutableList<StoragedEntity> = mutableListOf()

                for (city in entitiesFromServer) {
                    storagedEntities.add(StoragedCity(city))

                    city.stations!!.mapTo(storagedEntities) {
                        StoragedStation(it)
                    }
                }

                subscriber.onNext(storagedEntities)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(serverResponse.message()))
            }
        }

        subscriptions.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { viewState.updateStations(it) },
                                { viewState.displayError(it) }
                        )
        )
    }
}