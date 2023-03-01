package com.example.data.datasource

import android.content.ContentValues.TAG
import android.util.Log
import com.example.data.mapper.mapToData
import com.example.data.model.EquipmentEntity
import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class WarehouseDataSource() {
    private val database = Firebase.firestore

    fun deleteItem(name: String) {
        database.collection("Equipment").document(name)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun addItem(
        albumUri: String?,
        name : String,
        totalCnt: Int,
        currentCnt: Int) {
        val item =
            EquipmentEntity(albumUri, name, totalCnt, currentCnt)
        database.collection("Equipment").document(name)
            .set(item.mapToData())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun getItem() = callbackFlow {
        val itemRef = database.collection("Equipment").addSnapshotListener { snapshot, e ->
            val itemResponse = if (snapshot != null){
                val items = snapshot.toObjects(EquipmentData::class.java)
                ResponseData.Success(items)
            } else {
                ResponseData.Failure(e)
            }
            trySend(itemResponse)
        }
        awaitClose {
            itemRef.remove()
        }
    }
}