package com.example.domain.repository

import com.example.domain.data.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository  {
    fun signUpWithEmail(email: String, psw: String, name: String) : Flow<Result<User>>
    fun signInWithEmail(email: String, psw: String) :Flow<Result<Unit>>
    fun changeUserPsw(user: User, new_password: String) :Flow<Result<Unit>>
    fun deleteUser(uid: String) : Flow<Result<Unit>>
}