package com.example.domain.usecase.user

import com.example.domain.repository.UserDataRepository
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(email: String, psw: String, name: String) = userDataRepository.signUpWithEmail(email, psw, name)
}