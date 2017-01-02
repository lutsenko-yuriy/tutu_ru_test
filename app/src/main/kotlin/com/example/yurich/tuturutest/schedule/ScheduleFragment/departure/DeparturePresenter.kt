package com.example.yurich.tuturutest.schedule.ScheduleFragment.departure

import android.text.TextUtils
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedCity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import com.example.yurich.tuturutest.utils.DirectionConstants.DEPARTURE
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by yurich on 15.12.16.
 */
@InjectViewState
class DeparturePresenter : SchedulePresenter() {

    @Inject
    lateinit var resultQuery: ResultQuery

    @Inject
    lateinit var repository: Repository

    init {
        MainActivity.subcomponent.inject(this)
    }

    override fun retrieveAndShow(needle: String) {

        val mapper = rx.functions.Func1<StoragedStation, DisplayedStation>(::DisplayedStation)
        val listOfEntities = mutableListOf<DisplayedEntity>()
        var currentCity: DisplayedCity? = null

        subscriptions.add(
                repository.getStations()
                        .filter {
                            it.direction == DEPARTURE
                                    && (TextUtils.isEmpty(needle)
                                    || it.stationTitle.contains(needle, true))
                        }
                        .map(mapper)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    if (currentCity == null || currentCity!!.cityId != it.cityId) {
                                        currentCity = DisplayedCity(it)
                                        listOfEntities.add(currentCity!!)
                                    }

                                    listOfEntities.add(it)
                                },
                                { viewState.displayError("Не удалось отобразить станции") },
                                {
                                    viewState.displayStations(listOfEntities)
                                    viewState.onStationsLoaded()
                                }
                        )
        )
    }

    override fun passStation(station: DisplayedStation) {
        resultQuery.departureStation = station
        Log.v("Repo", resultQuery.toString())
        if (resultQuery.isReady()) {
            viewState.displayResult(resultQuery)
        }
    }

}