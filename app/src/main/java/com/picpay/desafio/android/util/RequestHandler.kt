package com.picpay.desafio.android.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class RequestHandler<out T> {
    data class Success<out T>(val value: T): RequestHandler<T>()
    data class GenericError(val throwable: Throwable, val code: Int? = 0, val message: String? = String()): RequestHandler<Nothing>()
    object NetworkError: RequestHandler<Nothing>()
}

suspend fun <T> call(apiCall: suspend () -> T): RequestHandler<T> {
    return withContext(Dispatchers.IO) {
        try {
            RequestHandler.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> RequestHandler.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    RequestHandler.GenericError(throwable, code, throwable.message())
                }
                else -> {
                    RequestHandler.GenericError(Throwable("generic error"), null)
                }
            }
        }
    }
}
