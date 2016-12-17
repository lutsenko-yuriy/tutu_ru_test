package com.example.yurich.tuturutest


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yurich.tuturutest.repository.DataPackage
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity


/**
 * A simple [Fragment] subclass.
 */
class ArrivalsFragment : Fragment(), ScheduleView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_stations_list, container, false)

    }

    override fun <Station> passSearchableData(data: DataPackage<Station>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateStations(entities: List<StoragedEntity>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayError(it: Throwable) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
