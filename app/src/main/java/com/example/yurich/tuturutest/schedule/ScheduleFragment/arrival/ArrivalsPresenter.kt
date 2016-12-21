package com.example.yurich.tuturutest.schedule.ScheduleFragment.arrival

import android.text.TextUtils
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.DirectionConstants
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by yurich on 18.12.16.
 */
@InjectViewState
class ArrivalsPresenter : SchedulePresenter() {

    @Inject
    lateinit var resultQuery: ResultQuery

    @Inject
    lateinit var repository: Repository

    init {
        StationsApp.appComponent.inject(this)
    }

    override fun retrieveAndShow(needle: String) {

        val mapper = rx.functions.Func1<List<StoragedStation>, List<StoragedEntity>>  {
            listOfStations ->

            val listOfArrivalStations = listOfStations.filter {
                it.direction == DirectionConstants.ARRIVAL
                && (TextUtils.isEmpty(needle)
                        || it.stationTitle.contains(needle, true))
            }

            var currentCity = StoragedCity(listOfArrivalStations[0])
            val listOfEntities = mutableListOf<StoragedEntity>(currentCity)

            for (station in listOfArrivalStations) {
                if (currentCity.cityId != StoragedCity(station).cityId) {
                    currentCity = StoragedCity(station)
                    listOfEntities.add(currentCity)
                }

                listOfEntities.add(station)
            }

            listOfEntities
        }

        subscriptions.add(
                repository.getStations()
                        .map(mapper)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { viewState.displayStations(it) },
                                { viewState.displayError("Не удалось найти станции") }
                        )
        )
    }

    override fun passStation(station: StoragedStation) {
        resultQuery.arrivalStation = station
        Log.v("Repo", resultQuery.toString())
        if (resultQuery.isReady()) {
            viewState.displayResult(resultQuery)
        }
    }

    fun  isArrivalStationChosen() = resultQuery.arrivalStation != null
}