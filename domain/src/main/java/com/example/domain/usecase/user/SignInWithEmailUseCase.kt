package com.example.domain.usecase.user

import com.example.domain.repository.UserDataRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(email: String, psw: String) = userDataRepository.signInWithEmail(email, psw)
}