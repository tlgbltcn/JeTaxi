package com.tlgbltcn.jetaxi.util

import okhttp3.ResponseBody

sealed class ResultHolder<T> {
    class Success<T>(val data: T) : ResultHolder<T>()
    class Error<T>(
        val message: String,
        val code: Int? = null,
        val data: ResponseBody?
    ) : ResultHolder<T>()
}
