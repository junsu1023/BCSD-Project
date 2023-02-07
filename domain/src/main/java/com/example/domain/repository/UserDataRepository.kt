package com.example.domain.repository

interface UserDataRepository {
    suspend fun signUpWithEmail(email: String, psw: String)
    suspend fun signInWithEmail(email: String, psw: String)
    suspend fun getUserName(uid: String): String
    suspend fun changeUserPws(uid: String)
    suspend fun deleteUser(uid: String)
}