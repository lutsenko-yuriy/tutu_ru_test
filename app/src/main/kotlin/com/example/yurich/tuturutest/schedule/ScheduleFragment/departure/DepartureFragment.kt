package com.example.yurich.tuturutest.schedule.ScheduleFragment.departure


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.mvp.ScheduleView
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import com.example.yurich.tuturutest.ui.OnStationListener
import com.example.yurich.tuturutest.ui.adapters.StationsAdapter
import com.example.yurich.tuturutest.utils.initialize
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_stations_list.*
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription


/**
 * A fragment which is supposed to work with Moxy MVP library
 * with a list of departure responseStations
 */

class DepartureFragment : MvpAppCompatFragment(), ScheduleView, OnStationListener {

    var subscriptions = CompositeSubscription()

    @InjectPresenter
    lateinit var presenter: DeparturePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_stations_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_of_stations.initialize(activity, this)

        subscriptions.add(RxTextView.textChanges(search_field)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.retrieveAndShow(it.toString())
                }
        )
    }

    override fun onDestroy() {
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        super.onDestroy()
    }

    override fun onStationClicked(station: DisplayedStation) {
        presenter.passStation(station)
        Snackbar.make(list_of_stations, getString(R.string.departure_added), Snackbar.LENGTH_LONG).show()
    }

    override fun displayStations(entities: List<DisplayedEntity>) {
        if ((list_of_stations.adapter as StationsAdapter).itemCount == 0) {
            setupStations(entities)
        } else {
            updateStations(entities)
        }
    }

    override fun setupStations(entities: List<DisplayedEntity>) {
        (list_of_stations.adapter as StationsAdapter).setupStations(entities)
    }

    override fun updateStations(entities: List<DisplayedEntity>) {
        (list_of_stations.adapter as StationsAdapter).updateStations(entities)
    }

    override fun displayError(it: Int) {
        Snackbar.make(list_of_stations, it, Snackbar.LENGTH_LONG).show()
    }

    override fun displayResult(query: ResultQuery) {
        (activity as MainActivity).displayResults(query)
    }
}
