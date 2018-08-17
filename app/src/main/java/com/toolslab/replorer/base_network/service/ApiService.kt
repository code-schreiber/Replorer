package com.toolslab.replorer.base_network.service

import com.toolslab.replorer.base_network.model.Repo

interface ApiService {

    fun listRepos(onSuccess: (List<Repo>) -> Unit, onError: (Exception) -> Unit)
}
