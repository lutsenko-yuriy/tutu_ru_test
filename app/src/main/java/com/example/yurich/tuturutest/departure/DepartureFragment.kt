package com.example.yurich.tuturutest.departure


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.ScheduleView
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.di.AppComponent
import com.example.yurich.tuturutest.repository.DataPackage
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.ui.StationsAdapter
import kotlinx.android.synthetic.main.fragment_stations_list.*


/**
 * A fragment which is supposed to work with Moxy MVP library
 * with a list of departure responseStations
 */

class DepartureFragment : MvpAppCompatFragment(), ScheduleView {

    @InjectPresenter
    lateinit var presenter: DeparturePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_stations_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        departure_stations.apply {
            layoutManager = LinearLayoutManager(activity)

            adapter = StationsAdapter()
            presenter.retrieveAndShow()
        }
    }

    override fun <Station> passSearchableData(data: DataPackage<Station>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateStations(entities: List<StoragedEntity>) {
        (departure_stations.adapter as StationsAdapter).updateStations(entities)
    }

    override fun displayError(it: Throwable) {
        Snackbar.make(departure_stations, it.message!!, Snackbar.LENGTH_LONG).show()
    }

}
