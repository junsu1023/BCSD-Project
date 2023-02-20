package com.example.domain.usecase.user

import com.example.domain.data.user.User
import com.example.domain.repository.UserRepository

class ChangeUserPswUseCase(
    private val userRepository: UserRepository
)
{
    operator fun invoke(user: User, new_password: String, new_password_check: String) = userRepository.changeUserPsw(user, new_password, new_password_check)
}