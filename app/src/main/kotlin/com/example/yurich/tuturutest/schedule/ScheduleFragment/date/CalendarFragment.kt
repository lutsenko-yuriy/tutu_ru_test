package com.example.yurich.tuturutest.schedule.ScheduleFragment.date


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.mvp.ScheduleView
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.result_alert_dialog_fragment.ResultAlertDialogFragment
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_stations_list.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : MvpAppCompatFragment(), ScheduleView {

    @InjectPresenter
    lateinit var presenter: CalendarPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        calendar.minDate = Date().time
        calendar.setOnDateChangeListener {
            calendarView, year, month, day ->

            val chosenDate = GregorianCalendar(year, month, day)
            calendar.date = chosenDate.timeInMillis
            presenter.passDate(chosenDate)
        }
    }

    override fun displayResult(query: ResultQuery) {
        ResultAlertDialogFragment(query).show(activity.fragmentManager, null)
    }

    override fun updateStations(entities: List<DisplayedEntity>) {
        // Nothing to do here
    }

    override fun displayStations(entities: List<DisplayedEntity>) {
        // Nothing to do here
    }

    override fun setupStations(entities: List<DisplayedEntity>) {
        // Nothing to do here
    }

    override fun displayError(it: String) {
        // Nothing to do here
    }

    override fun onStationsLoaded() {
        // Nothing to do
    }
}
