package com.toolslab.replorer.view.main

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.toolslab.replorer.base_repository.exception.NoConnectionException
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import org.junit.Before
import org.junit.Test

class RepoPresenterTest {

    private val error = Exception()
    private val repositoryViewModel1 = RepositoryViewModel("RepositoryViewModel1", "Description1", "UpdatedAt1", "Language1", "Licence1")
    private val repositoryViewModel2 = RepositoryViewModel("RepositoryViewModel2", "Description2", "UpdatedAt2", "Language2", "Licence2")
    private val viewModels = listOf(repositoryViewModel1, repositoryViewModel2)

    private val mockView: RepoContract.View = mock()

    private val underTest = RepoPresenter(mock())

    @Before
    fun setUp() {
        underTest.bind(mockView)
    }

    @Test
    fun onBound() {
        verify(mockView).showLoading()
    }

    @Test
    fun success() {
        underTest.success(viewModels)

        verify(mockView).showLoading()
        verify(mockView).setViewModels(listOf(repositoryViewModel2, repositoryViewModel1))
        verify(mockView).hideLoading()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun onErrorWithNoConnectionException() {
        underTest.error(NoConnectionException(error))

        verify(mockView).showLoading()
        verify(mockView).showNoConnectionError()
        verify(mockView).hideLoading()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun onErrorWithException() {
        underTest.error(error)

        verify(mockView).showLoading()
        verify(mockView).showDefaultError()
        verify(mockView).hideLoading()
        verifyNoMoreInteractions(mockView)
    }

}
