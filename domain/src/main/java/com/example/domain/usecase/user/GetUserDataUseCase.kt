package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository

class GetUserDataUseCase(
    private val userRepository: UserRepository
)
{
    operator fun invoke(uid: String) = userRepository.getUserData(uid)
}