package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository

class SignInWithEmailUseCase(
    private val userRepository: UserRepository
)
{
    operator fun invoke(email: String, psw: String) = userRepository.signInWithEmail(email, psw)
}