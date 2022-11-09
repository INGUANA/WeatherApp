package com.inguana.weatherapp

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.inguana.weatherapp.adapters.interfaces.ShowProgressBar

open class BaseActivity : AppCompatActivity(), ShowProgressBar {
    //====================================================
    //Base activity functionalities
    //====================================================
    private var progressBar: ProgressBar? = null
    override fun setContentView(layoutResID: Int) {
        val coordinatorLayout =
            layoutInflater.inflate(R.layout.base_activity, null) as CoordinatorLayout
        val frameLayout =
            coordinatorLayout.findViewById<FrameLayout>(R.id.activityFrameLayoutBaseActivity)
        progressBar = coordinatorLayout.findViewById(R.id.progressBarBaseActivity)
        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(coordinatorLayout)
    }

    override fun showProgressBar(isVisible: Boolean) {
        progressBar!!.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}