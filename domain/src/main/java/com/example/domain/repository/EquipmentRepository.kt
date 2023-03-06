package com.example.domain.repository

import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import kotlinx.coroutines.flow.Flow

interface EquipmentRepository {
    fun insertEquipmentData(equipmentData: EquipmentData)
    fun deleteEquipmentData(equipmentName : String)
    fun getEquipmentListData(): Flow<ResponseData<List<EquipmentData>>>
    fun getEquipmentData(equipmentName: String) : Flow<ResponseData<MutableMap<String, Any>?>>
}