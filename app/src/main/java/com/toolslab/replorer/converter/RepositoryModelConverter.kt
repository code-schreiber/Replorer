package com.toolslab.replorer.converter

import com.toolslab.replorer.base_network.model.Repo
import com.toolslab.replorer.base_repository.model.RepositoryViewModel

class RepositoryModelConverter : Converter<Repo, RepositoryViewModel> {

    internal val noDescription = "No description"
    internal val noDate = ""
    internal val noLanguage = "No language"
    internal val noLicense = "No license"

    override fun convert(source: Repo) =
            RepositoryViewModel(
                    source.name,
                    source.description.orDefault(noDescription),
                    source.updatedAt.orDefault(noDate),
                    source.language.orDefault(noLanguage),
                    source.license?.name.orDefault(noLicense)
            )

    private fun String?.orDefault(default: String): String =
            if (this.isNullOrBlank()) default else this!!

}
