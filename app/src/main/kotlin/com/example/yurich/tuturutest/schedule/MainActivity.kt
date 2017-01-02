package com.example.yurich.tuturutest.schedule

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.ScreenChanger
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.di.MainActivityComponent
import com.example.yurich.tuturutest.navigation.NavigationManagerImpl
import com.example.yurich.tuturutest.utils.isDbReseted
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ScreenChanger {

    companion object {
        lateinit var subcomponent: MainActivityComponent
    }

    val navigationManager = NavigationManagerImpl(supportFragmentManager, this)

    @SuppressWarnings("deprecated")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        subcomponent = StationsApp.appComponent.plusMainActivity()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            setDefaultFragment()
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_refresh) {
            applicationContext.isDbReseted()
            Snackbar.make(placeholder, "Пожалуйста, перезагрузите приложение", Snackbar.LENGTH_LONG).show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_basic_screen) {
            navigationManager.getScheduleFragment()
        } else if (id == R.id.nav_info) {
            navigationManager.getInfoFragment()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun setDefaultFragment() {
        navigationManager.getScheduleFragment()
    }

    override fun onScreenChanged(screen: Int) {
        nav_view.menu.getItem(screen).isChecked = true
    }
}