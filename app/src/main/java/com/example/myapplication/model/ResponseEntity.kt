package com.example.myapplication.model

sealed class ResponseEntity<out T> {
    data class Success<out T>(
        val data: T
    ): ResponseEntity<T>()

    data class Failure(
        val e: Exception?
    ): ResponseEntity<Nothing>()
}