package com.example.data.mapper

import com.example.data.model.ImageResponse
import com.example.domain.model.Images

object ImageMapper {
    fun mapToImage(imageResponse: ImageResponse): Images {
        return Images(imageResponse.uri)
    }
}