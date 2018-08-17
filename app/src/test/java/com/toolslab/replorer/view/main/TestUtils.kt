package com.toolslab.replorer.view.main

import com.toolslab.replorer.base_network.model.Repo
import org.mockito.invocation.InvocationOnMock

internal fun InvocationOnMock.invokeReposSuccess(repos: List<Repo>) =
        getArgument<((onSuccess: (List<Repo>)) -> Unit)>(0).invoke(repos)

internal fun InvocationOnMock.invokeError(e: Exception) =
        getArgument<((onError: (Exception)) -> Unit)>(1).invoke(e)
