package com.toolslab.replorer.converter

import com.toolslab.replorer.base_network.model.License
import com.toolslab.replorer.base_network.model.Repo

class JsonRepoConverter : Converter<String, List<Repo>> {

    private val name = "name"
    private val description = "description"
    private val updatedAt = "updated_at"
    private val language = "language"
    private val license = "license"

    override fun convert(source: String): List<Repo> {
        if (source.isBlank()) return emptyList()
        return source
                .replace("\n", "")
                .replace("\t", "")
                .split("},{")
                .map {
                    Repo(
                            it.getValueOf(name),
                            it.getValueOf(description),
                            it.getValueOf(updatedAt),
                            it.getValueOf(language),
                            License(it.getObjectOf(license).getValueOf(name))
                    )
                }
    }

    private fun String.getValueOf(key: String): String {
        val value = substringAfter("""$key":"""")
        if (value == this) return "" // Value was not found (is null in JSON)
        return value.substringBefore("""",""")
    }

    private fun String.getObjectOf(key: String): String {
        val value = substringAfter("""$key":{""")
        if (value == this) return "" // Value was not found (is null in JSON)
        return value.substringBefore("}")
    }

}


