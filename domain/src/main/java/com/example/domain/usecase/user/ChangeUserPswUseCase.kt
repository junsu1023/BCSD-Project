package com.example.domain.usecase.user

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.data.user.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class ChangeUserPswUseCase @Inject constructor(
    private val userRepository: UserRepository
)
{
    operator fun invoke(user: User, new_password: String, new_password_check: String, activity: AppCompatActivity) = userRepository.changeUserPsw(user, new_password, new_password_check, activity)
}