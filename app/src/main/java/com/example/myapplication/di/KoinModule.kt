package com.example.myapplication.di

import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.user.ChangeUserPswUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.SignInWithEmailUseCase
import com.example.domain.usecase.user.SignUpWithEmailUscCase
import com.example.myapplication.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { androidContext().contentResolver }

    single<UserRepository> { UserRepositoryImpl() }

    single { ChangeUserPswUseCase(get()) }
    single { DeleteUserUseCase(get()) }
    single { SignUpWithEmailUscCase(get()) }
    single { SignInWithEmailUseCase(get()) }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(get(), get(), get(), get())
    }
}