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

    private val _toastMessage = MutableLiveData<String>() //sigleLiveDatas 넣어보기
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun changePsw(user: User, new_password: String, new_password_check: String)
    {
        viewModelScope.launch {
            changeUserPswUseCase(user, new_password, new_password_check).collectLatest {
                it.onSuccess {
                    _toastMessage.value = "비밀번호가 변경되었습니다."
                }.onFailure {
                    _toastMessage.value = it.message
                }
            }
        }
    }

    fun deleteUser(uid: String)
    {
        viewModelScope.launch {
            deleteUserUseCase(uid).collectLatest {
                it.onSuccess {
                    _toastMessage.value = "회원탈퇴가 완료되었습니다"
                }.onFailure {
                    _toastMessage.value = it.message
                }
            }
        }

    }

    fun signInEmail(email: String, psw: String)
    {
        viewModelScope.launch {
            signInWithEmailUseCase(email, psw).collectLatest {
                it.onSuccess {
                    _toastMessage.value = "계정 생성 완료."
                }.onFailure {
                    _toastMessage.value = it.message
                }
            }
        }
    }

    fun signUpEmail(email: String, psw: String, name: String)
    {
        viewModelScope.launch {
            signUpWithEmailUseCase(email, psw, name).collectLatest {
                it.onSuccess {
                    _user.value = it
                    _toastMessage.value = "로그인에 성공 하였습니다."
                }.onFailure {
                    _toastMessage.value = it.message
                }
            }
        }
    }
}