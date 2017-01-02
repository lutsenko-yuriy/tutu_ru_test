package com.example.yurich.tuturutest.schedule.ScheduleFragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.arrival.ArrivalsFragment
import com.example.yurich.tuturutest.schedule.ScheduleFragment.date.CalendarFragment
import com.example.yurich.tuturutest.schedule.ScheduleFragment.departure.DepartureFragment

/**
 * Created by yurich on 18.12.16.
 */
class ScheduleAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val titles = listOf(
            "Станции отправления",
            "Станции назначения",
            "Даты"
    )

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return DepartureFragment()
            1 -> return ArrivalsFragment()
            2 -> return CalendarFragment()
            else -> {
                throw IllegalStateException("Unable to find corresponding fragment")
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}