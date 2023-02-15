package com.example.myapplication

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WarehouseRepositoryImpl(): WarehouseRepository {
    private val database = Firebase.firestore

    override suspend fun deleteItem(name: String) {
        database.collection("item").document(name)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    override suspend fun addItem(
        name: String,
        rentalUser : String,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean) {
        val item = WarehouseEntity(name, rentalUser, totalItem, currentItem, rentalState)
        database.collection("item").document(name)
            .set(item.toMap())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    override suspend fun getItem(name: String): List<WarehouseEntity> {
        val itemRef = database.collection("item").document(name)
        var itemData : List<WarehouseEntity>
        itemRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "Cached document data: ${document?.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }


}