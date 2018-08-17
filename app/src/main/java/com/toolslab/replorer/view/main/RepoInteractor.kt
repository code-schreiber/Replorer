package com.toolslab.replorer.view.main

import com.toolslab.replorer.base_repository.ErrorHandler
import com.toolslab.replorer.base_repository.RepositoryRepository
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import com.toolslab.replorer.converter.RepositoryModelConverter

class RepoInteractor constructor(internal val repositoryRepository: RepositoryRepository = RepositoryRepository(),
                                 internal val errorHandler: ErrorHandler = ErrorHandler(),
                                 internal val repositoryModelConverter: RepositoryModelConverter = RepositoryModelConverter()) {

    internal fun listRepositories(onSuccess: (List<RepositoryViewModel>) -> Unit, onError: (Exception) -> Unit) =
            repositoryRepository.listRepositories(
                    {
                        onSuccess(it.map {
                            repositoryModelConverter.convert(it)
                        })
                    },
                    {
                        onError(errorHandler.handle(it))
                    })

}
