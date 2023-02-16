package com.example.myapplication.repository

import com.example.myapplication.model.ResponseEntity
import com.example.myapplication.model.WarehouseEntity
import kotlinx.coroutines.flow.Flow

typealias Items = MutableList<WarehouseEntity>
typealias ItemsResponse = ResponseEntity<Items>

interface WarehouseRepository {

    suspend fun deleteItem(name: String)

    fun getItem(): Flow<ItemsResponse>

    suspend fun addItem(
        name: String,
        rentalUser : String,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean
    )
}