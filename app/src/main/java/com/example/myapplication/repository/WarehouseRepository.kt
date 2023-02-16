package com.example.myapplication.repository

import com.example.myapplication.model.WarehouseEntity
import com.google.android.gms.common.api.Response
import kotlinx.coroutines.flow.Flow

typealias Items = List<WarehouseEntity>
typealias ItemsResponse = Response<Items>

interface WarehouseRepository {

    suspend fun deleteItem(name: String)

    fun getItem(): Flow<Response>

    suspend fun addItem(
        name: String,
        rentalUser : String,
        totalItem: Int,
        currentItem: Int,
        rentalState: Boolean
    )
}