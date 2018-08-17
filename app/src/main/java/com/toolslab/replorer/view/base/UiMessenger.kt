package com.toolslab.replorer.view.base

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

class UiMessenger {

    internal fun showMessage(context: Context, @StringRes id: Int) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
    }

}
