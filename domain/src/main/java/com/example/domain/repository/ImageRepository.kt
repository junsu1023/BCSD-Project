package com.example.domain.repository

import com.example.domain.model.Images

interface ImageRepository {
    fun getImage(): Images
    fun getImageUri(position: Int): String
    fun loadImage()
}