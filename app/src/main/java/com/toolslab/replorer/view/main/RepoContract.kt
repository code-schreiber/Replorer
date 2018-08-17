package com.toolslab.replorer.view.main

import com.toolslab.replorer.base_mvp.BaseView
import com.toolslab.replorer.base_mvp.MvpPresenter
import com.toolslab.replorer.base_repository.model.RepositoryViewModel

interface RepoContract {

    interface Presenter : MvpPresenter<View>

    interface View : BaseView {
        fun showLoading()

        fun hideLoading()

        fun setViewModels(viewModels: List<RepositoryViewModel>)

    }

}
