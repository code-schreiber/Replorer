package com.toolslab.replorer.view.main

import android.os.Handler
import android.os.Looper
import android.support.annotation.VisibleForTesting
import com.toolslab.replorer.base_mvp.BasePresenter
import com.toolslab.replorer.base_repository.exception.NoConnectionException
import com.toolslab.replorer.base_repository.model.RepositoryViewModel

class RepoPresenter(private val repoInteractor: RepoInteractor = RepoInteractor())
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
                            success(it)
                        }
                    },
                    {
                        onUi {
                            error(it)
                        }
                    })
        }
        backgroundThread.start()
    }

    @VisibleForTesting
    internal fun success(viewModels: List<RepositoryViewModel>) {
        val sorted = viewModels.sortedWith(compareByDescending { it.updatedAt })
        view.setViewModels(sorted)
        view.hideLoading()
    }

    @VisibleForTesting
    internal fun error(exception: Exception) {
        when (exception) {
            is NoConnectionException -> view.showNoConnectionError()
            else -> view.showDefaultError()
        }
        view.hideLoading()
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
