package com.example.yurich.tuturutest.schedule.ScheduleFragment.departure


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.mvp.ScheduleView
import com.example.yurich.tuturutest.repository.DataPackage
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.result_alert_dialog_fragment.ResultAlertDialogFragment
import com.example.yurich.tuturutest.ui.OnStationListener
import com.example.yurich.tuturutest.ui.adapters.StationsAdapter
import kotlinx.android.synthetic.main.fragment_stations_list.*


/**
 * A fragment which is supposed to work with Moxy MVP library
 * with a list of departure responseStations
 */

class DepartureFragment : MvpAppCompatFragment(), ScheduleView, OnStationListener {

    @InjectPresenter
    lateinit var presenter: DeparturePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_stations_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_of_stations.apply {
            layoutManager = LinearLayoutManager(activity)

            adapter = StationsAdapter(this@DepartureFragment)
            presenter.retrieveAndShow()
        }

        search_field.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        findStations(p0.toString())
                    }

                }
        )
    }

    private fun findStations(needle: String) {
        list_of_stations.apply {
            (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    (adapter as StationsAdapter).find(
                            needle,
                            (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    ), 0
            )
        }
    }

    override fun updateStations(entities: List<StoragedEntity>) {
        (list_of_stations.adapter as StationsAdapter).updateStations(entities)
    }

    override fun displayError(it: Throwable) {
        Snackbar.make(list_of_stations, it.message!!, Snackbar.LENGTH_LONG).show()
    }

    override fun passStation(station: StoragedStation) {
        presenter.passStation(station)
        Snackbar.make(list_of_stations, "Станция отправления успешно добавлена", Snackbar.LENGTH_LONG).show()
    }

    override fun displayResult(query: ResultQuery) {
        ResultAlertDialogFragment(query).show(activity.fragmentManager, null)
    }
}
