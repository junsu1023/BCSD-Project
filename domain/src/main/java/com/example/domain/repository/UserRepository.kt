package com.example.domain.repository

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.data.user.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun signUpWithEmail(email: String, psw: String, name: String, activity: AppCompatActivity)
    fun signInWithEmail(email: String, psw: String, activity: AppCompatActivity) : FirebaseUser?
    fun changeUserPsw(user: User, new_password: String, new_password_check: String, activity: AppCompatActivity)
    fun deleteUser(uid: String, activity: AppCompatActivity)
}