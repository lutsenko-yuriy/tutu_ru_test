package com.example.yurich.tuturutest.navigation

import android.support.v4.app.FragmentManager
import com.example.yurich.tuturutest.CalendarFragment
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.information.InfoFragment

/**
 * Created by yurich on 14.12.16.
 */
class NavigationManagerImpl(val fragmentManager: FragmentManager) : NavigationManager {

    override fun getInfoFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.placeholder, InfoFragment())
                .commit()
    }

    override fun getScheduleFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.placeholder, CalendarFragment())
                .commit()
    }

}