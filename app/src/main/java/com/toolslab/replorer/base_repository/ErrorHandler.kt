package com.toolslab.replorer.base_repository

import com.toolslab.replorer.base_repository.exception.*
import java.io.IOException
import java.net.HttpURLConnection.*

class ErrorHandler {
    internal fun handle(e: Exception): Exception {
        return when (e) {
            is IOException -> NoConnectionException(e)
            is HttpException -> when (e.code) {
                HTTP_NOT_FOUND -> NotFoundException(e)
                HTTP_UNAUTHORIZED -> UnauthorizedException(e)
                HTTP_FORBIDDEN -> ForbiddenException(e)
                else -> RepositoryException(e)
            }
            else -> RepositoryException(e)
        }
    }

}
