package com.example.myapplication

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

interface WarehouseRepository {

    suspend fun updateItemAmount(
        name: String,
        image: Bitmap?,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean
    )

    suspend fun deleteItem(name: String)

    suspend fun getItem(name: String): List<WarehouseEntity>

    suspend fun addItem(
        name: String,
        image: Bitmap?,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean
    )
}