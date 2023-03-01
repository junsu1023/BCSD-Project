package com.example.data.model

sealed class ResponseEntity<out T> {
    data class Success<out T>(
        val data: T
    ): ResponseEntity<T>()

    data class Failure(
        val e: Exception?
    ): ResponseEntity<Nothing>()
}

val <T> ResponseEntity<T>.isSuccess get() = this is ResponseEntity.Success<T>
val <T> ResponseEntity<T>.isFailure get() = this is ResponseEntity.Success<T>

inline fun <T> ResponseEntity<out T>.onSuccess(block: (T) -> Unit) : ResponseEntity<T> {
    if(this is ResponseEntity.Success<T>) block(this.data)
    return this
}

inline fun <T> ResponseEntity<out T>.onFailure(block: (T) -> Unit) : ResponseEntity<T> {
    if(this is ResponseEntity.Success<T>) block(this.data)
    return this
}