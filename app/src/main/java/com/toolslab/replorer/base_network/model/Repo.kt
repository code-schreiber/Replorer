package com.toolslab.replorer.base_network.model

data class Repo(
        val name: String,
        val description: String?,
        val updatedAt: String?,
        val language: String?,
        val license: License?
)

