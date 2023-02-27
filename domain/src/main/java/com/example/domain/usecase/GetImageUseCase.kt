package com.example.domain.usecase

import com.example.domain.model.Images
import com.example.domain.repository.ImageRepository

class GetImageUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke(): Images = imageRepository.getImage()
}