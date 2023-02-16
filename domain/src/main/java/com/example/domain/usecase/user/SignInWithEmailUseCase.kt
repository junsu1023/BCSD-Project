package com.example.domain.usecase.user

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
)
{
    operator fun invoke(email: String, psw: String, activity: AppCompatActivity) = userRepository.signInWithEmail(email, psw, activity)
}