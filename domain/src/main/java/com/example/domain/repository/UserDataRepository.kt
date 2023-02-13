package com.example.domain.repository

import com.google.firebase.auth.FirebaseUser

interface UserDataRepository {
    fun signUpWithEmail(email: String, psw: String, name: String): Boolean
    fun signInWithEmail(email: String, psw: String): FirebaseUser?
    fun getUserName(uid: String): String
    fun changeUserPsw(uid: String, email: String,name: String, new_password: String, new_password_check: String): Boolean
    fun deleteUser(uid: String, psw: String, curPsw: String): Boolean
}