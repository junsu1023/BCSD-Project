package com.example.domain.usecase.user

import com.example.domain.repository.UserDataRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(uid: String, psw: String, curPsw: String) = userDataRepository.deleteUser(uid, psw, curPsw)
}