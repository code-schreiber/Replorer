package com.toolslab.replorer.view.main

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.replorer.base_network.model.Repo
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import org.amshove.kluent.shouldEqual
import org.junit.Assert.fail
import org.junit.Test

class RepoInteractorTest {

    private val repo1: Repo = mock()
    private val repo2: Repo = mock()
    private val repos = listOf(repo1, repo2)
    private val repositoryViewModel1: RepositoryViewModel = mock()
    private val repositoryViewModel2: RepositoryViewModel = mock()
    private val viewModels = listOf(repositoryViewModel1, repositoryViewModel2)

    private val underTest = RepoInteractor(mock(), mock(), mock())

    @Test
    fun listSpaces() {
        whenever(underTest.repositoryModelConverter.convert(repo1)).thenReturn(repositoryViewModel1)
        whenever(underTest.repositoryModelConverter.convert(repo2)).thenReturn(repositoryViewModel2)
        whenever(underTest.repositoryRepository.listRepositories(any(), any()))
                .thenAnswer {
                    it.invokeReposSuccess(repos)
                }

        underTest.listRepositories(
                {
                    it shouldEqual viewModels
                },
                {
                    fail()
                })
    }

    @Test
    fun listRepositoriesWithError() {
        val exception = Exception("an exception")
        val handledException = Exception("a handled exception")
        whenever(underTest.errorHandler.handle(exception)).thenReturn(handledException)
        whenever(underTest.repositoryRepository.listRepositories(any(), any()))
                .thenAnswer {
                    it.invokeError(exception)
                }

        underTest.listRepositories(
                {
                    fail()
                },
                {
                    it shouldEqual handledException
                })
    }

}
