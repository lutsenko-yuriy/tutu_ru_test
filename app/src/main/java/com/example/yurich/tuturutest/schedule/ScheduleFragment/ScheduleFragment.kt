package com.example.yurich.tuturutest.schedule.ScheduleFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yurich.tuturutest.R
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * Created by yurich on 18.12.16.
 */
class ScheduleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = ScheduleAdapter(activity.supportFragmentManager)
        viewpager.adapter = pagerAdapter
        tabs.setupWithViewPager(viewpager)
    }
}