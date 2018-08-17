package com.toolslab.replorer.view.base

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.toolslab.replorer.R
import org.junit.Test

class BaseActivityTest {

    private val underTest = BaseActivity(mock())

    @Test
    fun showNoConnectionError() {
        underTest.showNoConnectionError()

        verify(underTest.uiMessenger).showMessage(underTest, R.string.error_no_connection)
    }

    @Test
    fun showDefaultError() {
        underTest.showDefaultError()

        verify(underTest.uiMessenger).showMessage(underTest, R.string.error_default)
    }

}
