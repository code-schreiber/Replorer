package com.toolslab.replorer.base_repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.toolslab.replorer.base_network.model.Repo
import org.junit.Test

class RepositoryRepositoryTest {

    private val mockOnSuccess: (List<Repo>) -> Unit = mock()
    private val mockOnError: (Exception) -> Unit = mock()

    private val underTest = RepositoryRepository(mock())

    @Test
    fun listRepositories() {
        underTest.listRepositories(mockOnSuccess, mockOnError)

        verify(underTest.gitHubApiService).listRepos(mockOnSuccess, mockOnError)
    }

}
