package com.example.yurich.tuturutest.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.di.SplashActivityComponent
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.splash.RetainFragment
import com.example.yurich.tuturutest.splash.RetainFragment.Companion.STATE_ERROR
import kotlinx.android.synthetic.main.splash_screen.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    val FRAG_TAG = "Splash"

    companion object {
        lateinit var subcomponent: SplashActivityComponent
    }

    @Inject
    lateinit var repository: Repository

    var retainFragment: RetainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        subcomponent = StationsApp.appComponent.plusSplashActivity()

        retainFragment = supportFragmentManager.findFragmentByTag(FRAG_TAG) as RetainFragment?
        if (retainFragment == null) {
            retainFragment = RetainFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(retainFragment, FRAG_TAG)
                    .commit()
        }

        if (retainFragment?.currentState == STATE_ERROR) {
            displayError()
        }
    }

    fun displayError() {
        splash_text.text = getString(R.string.error_message)
        splash_text.textSize = 16f

        progress_bar.visibility = View.GONE
    }

    fun displayProgress(stationsAmount: Int) {
        progress_text.text =
                resources.getQuantityString(R.plurals.stations, stationsAmount, stationsAmount)
    }
}