package com.example.data.repository

import android.content.ContentResolver
import android.provider.MediaStore
import com.example.domain.model.Images
import com.example.domain.repository.ImageRepository
import com.example.data.data.ImageData.imageData
import com.example.data.mapper.ImageMapper
import com.example.data.model.ImageResponse

class ImageRepositoryImpl(private  val contentResolver: ContentResolver): ImageRepository {
    override fun getImage(): Images = ImageMapper.mapToImage(imageData)

    override fun getImageUri(position: Int): String = imageData.uri

    override fun loadImage() {
        imageData = ImageResponse(MediaStore.Images.Media._ID)
    }

}