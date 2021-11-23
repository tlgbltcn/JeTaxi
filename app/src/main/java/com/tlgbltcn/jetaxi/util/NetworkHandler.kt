package com.tlgbltcn.jetaxi.util

import retrofit2.Response
import java.lang.Exception

suspend fun <T> networkHandler(
    onCall: suspend () -> Response<T>,
    onSuccess: suspend (T?) -> Unit,
    onFailure: suspend ((code: Int, message: String) -> Unit)
) {
    return try {
        val result = onCall.invoke()
        if ((result.isSuccessful) and (result.body() != null)) {
            onSuccess.invoke(result.body())
        } else {
            onFailure.invoke(result.code(), result.message())
        }
    } catch (e: Exception) {
        onFailure.invoke(-1, e.message ?: "")
    }
}