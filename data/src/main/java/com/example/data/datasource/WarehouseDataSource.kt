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

class WarehouseDataSource {
    private val database = Firebase.firestore

    fun deleteItem(name: String) {
        database.collection("Equipment").document(name)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun addItem(equipmentData: EquipmentData) {
        val item = hashMapOf(
            "name" to equipmentData.name,
            "albumUri" to equipmentData.albumUri,
            "totalCnt" to equipmentData.totalCnt,
            "currentCnt" to equipmentData.currentCnt
        )
        database.collection("Equipment").document(equipmentData.name)
            .set(item)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun getItemList() = callbackFlow {
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

    fun getItem(name: String) = callbackFlow {
        val itemRef = database.collection("Equipment").document(name).addSnapshotListener { snapshot, e ->
            val itemResponse = if (snapshot != null){
                val items = snapshot.data
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