package com.example.myapplication

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WarehouseRepositorympl(): WarehouseRepository {
    val test = "test"
    private val database = Firebase.database
    private val itemReference = database.getReference(test)

    override suspend fun deleteItem(name: String) {
        val key = itemReference.child(name).push().key
        if (key == null) {
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }

        itemReference.child(key).removeValue()
    }

    override suspend fun addItem(
        name: String,
        image: Bitmap?,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean) {
        val item = WarehouseEntity(name, image, totalItem, currentItem, rentalState)

        itemReference.child(test).setValue(item)
    }

    override suspend fun updateItemAmount(
        name: String,
        image: Bitmap?,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean
        ) {

        val key = itemReference.child(name).push().key
        if (key == null) {
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }

        val item = WarehouseEntity(name, image, totalItem, currentItem, rentalState)
        val itemValues = item.toMap()

        val itemUpdates = hashMapOf<String, Any>(
            "$key" to itemValues
        )
        itemReference.updateChildren(itemUpdates)
    }

    override suspend fun getItem(name: String): List<WarehouseEntity> {
        val treeList: List<WarehouseEntity>
        itemReference.get().addOnSuccessListener {
            // 데이터 가져오기
        }
    }
}