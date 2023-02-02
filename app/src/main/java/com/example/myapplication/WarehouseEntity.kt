package com.example.myapplication

import android.graphics.Bitmap

data class WarehouseEntity(
    var name: String,
    var image: Bitmap? = null,
    var totalItem: Int,
    var currentItem: Int,
    var rentalState: Boolean
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "image" to image,
            "totalItem" to totalItem,
            "currentItem" to currentItem,
            "rentalState" to rentalState
        )
    }
}
