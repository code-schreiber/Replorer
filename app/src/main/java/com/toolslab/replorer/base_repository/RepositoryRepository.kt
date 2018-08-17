package com.toolslab.replorer.base_repository

import com.toolslab.replorer.base_network.GitHubApiService
import com.toolslab.replorer.base_network.model.Repo

class RepositoryRepository(internal val gitHubApiService: GitHubApiService = GitHubApiService()) {

    fun listRepositories(onSuccess: (List<Repo>) -> Unit, onError: (Exception) -> Unit) =
            gitHubApiService.listRepos(onSuccess, onError)

}
