package com.example.yurich.tuturutest.schedule.ScheduleFragment.arrival

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.result_alert_dialog_fragment.ResultAlertDialogFragment
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

    override fun retrieveAndShow() {

        val mapper = rx.functions.Func1<List<StoragedStation>, List<StoragedEntity>>  {
            listOfStations ->

            val listOfArrivalStations = listOfStations.filter {
                it.direction == DirectionConstants.DEPARTURE
            }

            var currentCity = StoragedCity(listOfArrivalStations[0])
            val listOfEntities = mutableListOf<StoragedEntity>(currentCity)

            for (station in listOfArrivalStations) {
                if (currentCity != StoragedCity(station)) {
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
                                { viewState.updateStations(it) },
                                { viewState.displayError(it) }
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
}