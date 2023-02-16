package com.example.domain.usecase.user

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
)
{
    operator fun invoke(uid: String, activity: AppCompatActivity) = userRepository.deleteUser(uid, activity)
}