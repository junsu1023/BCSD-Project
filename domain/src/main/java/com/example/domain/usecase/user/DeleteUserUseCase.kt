package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository
)
{
    operator fun invoke(uid: String) = userRepository.deleteUser(uid)
}