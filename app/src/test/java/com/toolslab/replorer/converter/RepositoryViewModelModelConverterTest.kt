package com.toolslab.replorer.converter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.replorer.base_network.model.License
import com.toolslab.replorer.base_network.model.Repo
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import org.amshove.kluent.shouldEqual
import org.junit.Test

class RepositoryViewModelModelConverterTest {

    private val repositoryViewModel = RepositoryViewModel("a name", "a description", "a date", "a language", "a license")

    private val mockRepo: Repo = mock()
    private val mockLicense: License = mock()

    private val underTest = RepositoryModelConverter()

    @Test
    fun convert() {
        whenever(mockRepo.name).thenReturn(repositoryViewModel.name)
        whenever(mockRepo.description).thenReturn(repositoryViewModel.description)
        whenever(mockRepo.updatedAt).thenReturn(repositoryViewModel.updatedAt)
        whenever(mockRepo.language).thenReturn(repositoryViewModel.language)
        whenever(mockRepo.license).thenReturn(mockLicense)
        whenever(mockLicense.name).thenReturn(repositoryViewModel.license)

        val result = underTest.convert(mockRepo)

        result shouldEqual repositoryViewModel
    }

    @Test
    fun convertWithMissingInfos() {
        whenever(mockRepo.name).thenReturn(repositoryViewModel.name)
        whenever(mockRepo.description).thenReturn("")
        whenever(mockRepo.updatedAt).thenReturn(repositoryViewModel.updatedAt)

        val result = underTest.convert(mockRepo)

        result.name shouldEqual repositoryViewModel.name
        result.description shouldEqual underTest.noDescription
        result.updatedAt shouldEqual repositoryViewModel.updatedAt
        result.language shouldEqual underTest.noLanguage
        result.license shouldEqual underTest.noLicense
    }

}
