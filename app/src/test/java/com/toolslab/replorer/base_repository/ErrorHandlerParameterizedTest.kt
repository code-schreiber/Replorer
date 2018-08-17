package com.toolslab.replorer.base_repository

import com.toolslab.replorer.base_repository.exception.*
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException
import java.net.HttpURLConnection.*
import kotlin.reflect.KClass

@RunWith(Parameterized::class)
class ErrorHandlerParameterizedTest(private val input: Exception, private val expected: KClass<*>) {

    private val underTest = ErrorHandler()

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf(IOException(), NoConnectionException::class),

                arrayOf(HttpException(HTTP_NOT_FOUND), NotFoundException::class),
                arrayOf(HttpException(HTTP_UNAUTHORIZED), UnauthorizedException::class),
                arrayOf(HttpException(HTTP_FORBIDDEN), ForbiddenException::class),
                arrayOf(HttpException(HTTP_NOT_IMPLEMENTED), RepositoryException::class),

                arrayOf(Exception(), RepositoryException::class)
        )

    }

    @Test
    fun handle() {
        val exception = underTest.handle(input)
        exception shouldBeInstanceOf expected
        exception.cause shouldEqual input
    }

}
