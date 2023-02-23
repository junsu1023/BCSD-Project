package com.example.domain.usecase

import com.example.domain.repository.ImageRepository

class GetImageUriUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke(position: Int): String = imageRepository.getImageUri(position)
}