package com.toolslab.replorer.view.main

import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import org.junit.Before
import org.junit.Test

class RepoActivityTest {

    private val repositoryViewModel1 = RepositoryViewModel("RepositoryViewModel1", "Description1", "UpdatedAt1", "Language1", "Licence1")
    private val repositoryViewModel2 = RepositoryViewModel("RepositoryViewModel2", "Description2", "UpdatedAt2", "Language2", "Licence2")
    private val viewModels = listOf(repositoryViewModel1, repositoryViewModel2)

    private val underTest = RepoActivity(mock())

    @Before
    fun setUp() {
        underTest.loading = mock()
        underTest.recyclerView = mock()
    }

    @Test
    fun showLoading() {
        underTest.showLoading()

        verify(underTest.loading).visibility = VISIBLE
        verify(underTest.recyclerView).visibility = GONE
    }

    @Test
    fun hideLoading() {
        underTest.hideLoading()

        verify(underTest.loading).visibility = GONE
        verify(underTest.recyclerView).visibility = VISIBLE
    }

    @Test
    fun setViewModels() {
        underTest.setViewModels(viewModels)

        verify(underTest.recyclerView).setHasFixedSize(true)
        verify(underTest.recyclerView).layoutManager = any<LinearLayoutManager>()
        verify(underTest.recyclerView).adapter = any<RepositoriesAdapter>()
    }

}
