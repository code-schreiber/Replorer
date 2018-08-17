package com.toolslab.replorer.view.main

import android.os.Handler
import android.os.Looper
import com.toolslab.replorer.base_mvp.BasePresenter
import com.toolslab.replorer.base_repository.exception.NoConnectionException

class RepoPresenter(internal val repoInteractor: RepoInteractor = RepoInteractor())
    : BasePresenter<RepoContract.View>(), RepoContract.Presenter {

    internal lateinit var backgroundThread: Thread

    override fun onBound(view: RepoContract.View) {
        view.showLoading()
        listRepositories()
    }

    override fun onUnbound(view: RepoContract.View) {
        if (::backgroundThread.isInitialized) {
            backgroundThread.interrupt()
        }
    }

    private fun listRepositories() {
        backgroundThread = onBackground {
            repoInteractor.listRepositories(
                    {
                        onUi {
                            view.setViewModels(it.sortedWith(compareByDescending { it.updatedAt }))
                            view.hideLoading()
                        }
                    },
                    {
                        onUi {
                            when (it) {
                                is NoConnectionException -> view.showNoConnectionError()
                                else -> view.showDefaultError()
                            }
                            view.hideLoading()
                        }
                    })
        }
        backgroundThread.start()
    }

    private fun onUi(function: () -> Unit) {
        Handler(Looper.getMainLooper()).post {
            function.invoke()
        }
    }

    private fun onBackground(function: () -> Unit): Thread {
        return Thread {
            kotlin.run {
                function.invoke()
            }
        }
    }

}
