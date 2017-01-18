package com.example.yurich.tuturutest.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.di.subcomponents.SplashActivityComponent
import com.example.yurich.tuturutest.di.subcomponents.SplashActivityModule
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.splash.RetainFragment.Companion.STATE_ERROR
import com.example.yurich.tuturutest.utils.setDbUpdated
import kotlinx.android.synthetic.main.splash_screen.*

class SplashActivity : AppCompatActivity() {

    val FRAG_TAG = "Splash"

    companion object {
        var subcomponent: SplashActivityComponent? = null
    }

    var retainFragment: RetainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        subcomponent = StationsApp.appComponent.plus(SplashActivityModule(this))

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

    fun onDone() {
        setDbUpdated(true)
        startActivity(Intent(this, MainActivity::class.java))
        subcomponent = null
        finish()
    }

    fun displayError() {
        splash_text.text = getString(R.string.error_message)
        splash_text.textSize = 16f

        progress_bar.visibility = View.GONE
        subcomponent = null
    }

}
