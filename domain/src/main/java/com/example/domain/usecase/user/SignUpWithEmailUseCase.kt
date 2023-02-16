package com.example.domain.usecase.user

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
)
{
    operator fun invoke(email: String, psw: String, name: String, activity: AppCompatActivity) = userRepository.signUpWithEmail(email, psw, name, activity)
}