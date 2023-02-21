package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository

class SignUpWithEmailUscCase(
    private val userRepository: UserRepository
)
{
    operator fun invoke(email: String, psw: String, name: String) = userRepository.signUpWithEmail(email, psw, name)
}