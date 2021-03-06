package com.example.yurich.tuturutest.schedule.ScheduleFragment.departure

import android.util.Log
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedCity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import javax.inject.Inject

/**
 * Created by yurich on 15.12.16.
 */
class DeparturePresenter : SchedulePresenter() {

    @Inject
    lateinit var resultQuery: ResultQuery

    @Inject
    lateinit var repository: Repository

    init {
        MainActivity.subcomponent.inject(this)
    }

    override fun retrieveAndShow(filter: String) {

        val listOfEntities = mutableListOf<DisplayedEntity>()
        var currentCity: DisplayedCity? = null

        for (station in repository.getDepartureStations(filter).map(::DisplayedStation)) {
            if (currentCity == null || currentCity.cityId != station.cityId) {
                currentCity = DisplayedCity(station)
                listOfEntities.add(currentCity)
            }

            listOfEntities.add(station)
        }

        viewState.displayStations(listOfEntities)
    }

    override fun passStation(station: DisplayedStation) {
        resultQuery.departureStation = station
        Log.v("Repo", resultQuery.toString())
        viewState.displayResult(resultQuery)
    }

}