package com.example.domain.usecase

import com.example.domain.repository.ImageRepository

class LoadImageUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke() {
        return imageRepository.loadImage()
    }
}