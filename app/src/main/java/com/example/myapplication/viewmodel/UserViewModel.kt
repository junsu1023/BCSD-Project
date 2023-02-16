package com.example.myapplication.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.domain.data.user.User
import com.example.domain.usecase.user.ChangeUserPswUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.SignInWithEmailUseCase
import com.example.domain.usecase.user.SignUpWithEmailUseCase
import com.google.firebase.auth.FirebaseUser

class UserViewModel(
    private val changeUserPswUseCase: ChangeUserPswUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModel(){

    fun changePsw(user: User, new_password: String, new_password_check: String, activity: AppCompatActivity)
    {
        changeUserPswUseCase(user, new_password, new_password_check, activity)
    }

    fun deleteUser(uid: String, activity: AppCompatActivity)
    {
        deleteUserUseCase(uid, activity)
    }

    fun signInEmail(email: String, psw: String, activity: AppCompatActivity): FirebaseUser?
    {
        return signInWithEmailUseCase(email, psw, activity)
    }

    fun signUpEmail(email: String, psw: String, name: String, activity: AppCompatActivity)
    {
        signUpWithEmailUseCase(email, psw, name, activity)
    }
}