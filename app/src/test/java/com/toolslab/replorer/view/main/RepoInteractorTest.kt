package com.toolslab.replorer.view.main

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.replorer.base_network.model.Repo
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import org.amshove.kluent.shouldEqual
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.invocation.InvocationOnMock

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
                    it.getOnSuccessArgument().invoke(repos)
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
                    it.getOnErrorArgument().invoke(exception)
                }

        underTest.listRepositories(
                {
                    fail()
                },
                {
                    it shouldEqual handledException
                })
    }

    private fun InvocationOnMock.getOnSuccessArgument() = getArgument<((a: (List<Repo>)) -> Unit)>(0)
    private fun InvocationOnMock.getOnErrorArgument() = getArgument<((b: (Exception)) -> Unit)>(1)

}
