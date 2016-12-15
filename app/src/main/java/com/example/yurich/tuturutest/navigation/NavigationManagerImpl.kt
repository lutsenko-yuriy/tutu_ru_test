package com.example.yurich.tuturutest.navigation

import android.support.v4.app.FragmentManager
import com.example.yurich.tuturutest.CalendarFragment
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.departure.DepartureFragment
import com.example.yurich.tuturutest.information.InfoFragment
import com.example.yurich.tuturutest.repository.ScreenChanger

/**
 * Created by yurich on 14.12.16.
 */
class NavigationManagerImpl(
        val fragmentManager: FragmentManager,
        val screenChanger: ScreenChanger
) : NavigationManager {

    companion object {
        private val CHOOSING_ROUTES = 0
        private val INFO_SCREEN = 1
    }

    override fun getInfoFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.placeholder, InfoFragment())
                .commit()
        screenChanger.onScreenChanged(INFO_SCREEN)
    }

    override fun getScheduleFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.placeholder, DepartureFragment())
                .commit()
        screenChanger.onScreenChanged(CHOOSING_ROUTES)
    }

}