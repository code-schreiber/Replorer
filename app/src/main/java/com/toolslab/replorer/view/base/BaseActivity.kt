package com.toolslab.replorer.view.base

import android.annotation.SuppressLint
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import com.toolslab.replorer.R
import com.toolslab.replorer.base_mvp.BaseView

@SuppressLint("Registered") // BaseActivity should not go in the manifest
open class BaseActivity(internal val uiMessenger: UiMessenger = UiMessenger())
    : AppCompatActivity(), BaseView {

    override fun showNoConnectionError() {
        showMessage(R.string.error_no_connection)
    }

    override fun showDefaultError() {
        showMessage(R.string.error_default)
    }

    internal fun showMessage(@StringRes id: Int) {
        uiMessenger.showMessage(this, id)
    }

}
