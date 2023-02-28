package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.user.User
import com.example.domain.usecase.user.ChangeUserPswUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.SignInWithEmailUseCase
import com.example.domain.usecase.user.SignUpWithEmailUscCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserViewModel(
    private val changeUserPswUseCase: ChangeUserPswUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUscCase
) : ViewModel(){

    private val _finishCheck = MutableLiveData<Boolean>()
    val finishCheck: LiveData<Boolean> get() = _finishCheck

    fun changePsw(user: User, new_password: String)
    {
        viewModelScope.launch {
            changeUserPswUseCase(user, new_password).collectLatest {
                it.onSuccess {
                    _finishCheck.value = true
                }.onFailure {
                    _finishCheck.value = false
                }
            }
        }
    }

    fun deleteUser(uid: String)
    {
        viewModelScope.launch {
            deleteUserUseCase(uid).collectLatest {
                it.onSuccess {
                    _finishCheck.value = true
                }.onFailure {
                    _finishCheck.value = false
                }
            }
        }

    }

    fun signInEmail(email: String, psw: String)
    {
        viewModelScope.launch {
            signInWithEmailUseCase(email, psw).collectLatest {
                it.onSuccess {
                    _finishCheck.value = true
                }.onFailure {
                    _finishCheck.value = false
                }
            }
        }
    }

    fun signUpEmail(email: String, psw: String, name: String)
    {
        viewModelScope.launch {
            signUpWithEmailUseCase(email, psw, name).collectLatest { result ->
                result.onSuccess {
                    _finishCheck.value = true
                }.onFailure {
                    _finishCheck.value = false
                }
            }
        }
    }
}
