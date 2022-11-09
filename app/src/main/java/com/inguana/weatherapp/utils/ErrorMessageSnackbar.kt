package com.inguana.weatherapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object ErrorMessageSnackbar {
    fun showErrorMessage(rootLayout: View?, errorMessage: String?) {
        val snackbar = Snackbar
            .make(rootLayout!!, errorMessage!!, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}