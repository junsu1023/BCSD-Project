package com.example.domain.usecase.user

import com.example.domain.repository.UserDataRepository
import javax.inject.Inject

class SignInWithEmail @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(email: String, psw: String) = userDataRepository.signInWithEmail(email, psw)
}