package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.user.ChangeUserPswUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.SignInWithEmailUseCase
import com.example.domain.usecase.user.SignUpWithEmailUseCase

class UserViewModel(
    private val changeUserPswUseCase: ChangeUserPswUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModel(){

    fun changePsw(uid: String, email: String,name: String, new_password: String, new_password_check: String) :Boolean
    {
        return changeUserPswUseCase(uid, email, name, new_password, new_password_check)
    }

    fun deleteUser(uid: String, psw: String, curPsw: String): Boolean
    {
        return deleteUserUseCase(uid, psw, curPsw)
    }

    fun signInEmail(email: String, psw: String): FirebaseUser?
    {
        return signInWithEmailUseCase(email, psw)
    }

    fun signUpEmail(email: String, psw: String, name: String): Boolean
    {
        return signUpWithEmailUseCase(email, psw, name)
    }
}