package com.example.domain.repository


import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import kotlinx.coroutines.flow.Flow

typealias Items = MutableList<EquipmentData>
typealias ItemsResponse = ResponseData<Items>

interface WarehouseRepository {

    suspend fun deleteItem(name: String)

    fun getItem(): Flow<ItemsResponse>

    suspend fun addItem(
        albumUri: String?,
        name : String,
        totalCnt: Int,
        currentCnt: Int
    )
}