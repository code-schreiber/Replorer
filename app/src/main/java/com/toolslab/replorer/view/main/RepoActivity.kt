package com.toolslab.replorer.view.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import com.toolslab.replorer.R
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import com.toolslab.replorer.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity(private val presenter: RepoContract.Presenter = RepoPresenter()) : BaseActivity(), RepoContract.View {

    internal lateinit var loading: ProgressBar

    internal lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        bindViews()
        presenter.bind(this)
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun showLoading() {
        loading.visibility = VISIBLE
        recyclerView.visibility = GONE
    }

    override fun hideLoading() {
        loading.visibility = GONE
        recyclerView.visibility = VISIBLE
    }

    override fun setViewModels(viewModels: List<RepositoryViewModel>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RepositoriesAdapter(viewModels)
    }

    private fun bindViews() {
        loading = activity_repo_loading
        recyclerView = activity_repo_recycler_view
    }

}
