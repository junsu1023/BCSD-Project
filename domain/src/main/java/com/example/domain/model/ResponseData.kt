package com.example.domain.model

sealed class ResponseData<out T> {
    data class Success<out T>(
        val data: T
    ): ResponseData<T>()

    data class Failure(
        val e: Exception?
    ): ResponseData<Nothing>()
}

val <T> ResponseData<T>.isSuccess get() = this is ResponseData.Success<T>
val <T> ResponseData<T>.isFailure get() = this is ResponseData.Success<T>

inline fun <T> ResponseData<out T>.onSuccess(block: (T) -> Unit) : ResponseData<T> {
    if(this is ResponseData.Success<T>) block(this.data)
    return this
}

inline fun <T> ResponseData<out T>.onFailure(block: (T) -> Error) : ResponseData<T> {
    if(this is ResponseData.Success<T>) block(this.data)
    return this
}