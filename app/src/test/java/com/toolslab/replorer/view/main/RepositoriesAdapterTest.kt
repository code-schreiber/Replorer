package com.toolslab.replorer.view.main

import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import org.amshove.kluent.shouldEqual
import org.junit.Test

class RepositoriesAdapterTest {

    private val repositoryViewModel1 = RepositoryViewModel("RepositoryViewModel1", "Description1", "UpdatedAt1", "Language1", "Licence1")
    private val repositoryViewModel2 = RepositoryViewModel("RepositoryViewModel2", "Description2", "UpdatedAt2", "Language2", "Licence2")
    private val viewModels = listOf(repositoryViewModel1, repositoryViewModel2)

    private val underTest = RepositoriesAdapter(viewModels)

    @Test
    fun getItemCount() {
        underTest.itemCount shouldEqual viewModels.size
    }

    @Test
    fun extractRelativeTimeSpanFromEmptyString() {
        underTest.extractRelativeTimeSpan("") shouldEqual ""
    }

}
