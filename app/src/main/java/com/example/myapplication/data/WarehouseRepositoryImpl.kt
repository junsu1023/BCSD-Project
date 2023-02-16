package com.example.myapplication.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.myapplication.model.ResponseEntity
import com.example.myapplication.model.WarehouseEntity
import com.example.myapplication.repository.ItemsResponse
import com.example.myapplication.repository.WarehouseRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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

    override fun getItem() = callbackFlow {
        val itemRef = database.collection("item").addSnapshotListener { snapshot, e ->
            val itemResponse = if (snapshot != null){
                val items = snapshot.toObjects(WarehouseEntity::class.java)
                ResponseEntity.Success(items)
            } else {
                ResponseEntity.Failure(e)
            }
            trySend(itemResponse)
        }
        awaitClose {
            itemRef.remove()
        }
    }
}