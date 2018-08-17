package com.toolslab.replorer.base_network

import com.toolslab.replorer.base_network.ApiEndpoint.API_BASE_URL
import com.toolslab.replorer.base_network.ApiEndpoint.Endpoint.REPOS
import com.toolslab.replorer.base_network.ApiEndpoint.HttpRequestMethod.GET
import com.toolslab.replorer.base_network.model.Repo
import com.toolslab.replorer.base_network.service.ApiService
import com.toolslab.replorer.base_repository.exception.HttpException
import com.toolslab.replorer.converter.JsonRepoConverter
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL

class GitHubApiService(private val jsonRepoConverter: JsonRepoConverter = JsonRepoConverter()) : ApiService {

    override fun listRepos(onSuccess: (List<Repo>) -> Unit, onError: (Exception) -> Unit) {
        try {
            val response = makeEndpointCall(REPOS)
            val repos = jsonRepoConverter.convert(response)
            onSuccess(repos)
        } catch (e: IOException) {
            onError(e)
        } catch (e: HttpException) {
            onError(e)
        }
    }

    private fun makeEndpointCall(endpoint: String): String {
        val url = URL("$API_BASE_URL$endpoint")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = GET
        connection.connect()
        val responseCode = connection.responseCode
        if (responseCode != HTTP_OK) throw HttpException(responseCode)
        val response = readResponse(connection.inputStream)
        connection.disconnect()
        return response
    }

    private fun readResponse(inputStream: InputStream): String {
        val totalLines = StringBuilder(inputStream.available())
        val reader = BufferedReader(InputStreamReader(inputStream))
        var singleLine = reader.readLine()
        while (singleLine != null) {
            totalLines.append(singleLine)
            singleLine = reader.readLine()
        }
        reader.close()
        inputStream.close()
        return totalLines.toString()
    }

}
