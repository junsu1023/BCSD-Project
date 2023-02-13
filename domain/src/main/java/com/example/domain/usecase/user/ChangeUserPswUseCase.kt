package com.example.domain.usecase.user

import com.example.domain.repository.UserDataRepository
import javax.inject.Inject

class ChangeUserPswUseCase  @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(uid: String, email: String,name: String, new_password: String, new_password_check: String) = userDataRepository.changeUserPsw(uid, email, name, new_password, new_password_check)
}