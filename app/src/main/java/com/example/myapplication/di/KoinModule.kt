package com.example.myapplication.di

import com.example.data.repository.EquipmentRepositoryImpl
import com.example.data.repository.ImageRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.EquipmentRepository
import com.example.domain.repository.ImageRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.*
import com.example.domain.usecase.user.ChangeUserPswUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.SignInWithEmailUseCase
import com.example.domain.usecase.user.SignUpWithEmailUscCase
import com.example.myapplication.activity.EquipmentListActivity
import com.example.myapplication.viewmodel.ImageViewModel
import com.example.myapplication.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { androidContext().contentResolver }

    single<ImageRepository> { ImageRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl() }
    single<EquipmentRepository> { EquipmentRepositoryImpl(get()) }

    single { ChangeUserPswUseCase(get()) }
    single { DeleteUserUseCase(get()) }
    single { SignUpWithEmailUscCase(get()) }
    single { SignInWithEmailUseCase(get()) }
    single { GetImageUriUseCase(get()) }
    single { GetImageUseCase(get()) }
    single { LoadImageUseCase(get()) }
    single { DeleteEquipmentUseCase(get()) }
    single { GetEquipmentDataListUseCase(get()) }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(get(), get(), get(), get())
    }
    viewModel {
        ImageViewModel(get(), get(), get())
    }
}

