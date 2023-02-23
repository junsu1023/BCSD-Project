package com.example.domain.repository

import com.example.domain.model.EquipmentData

interface EquipmentRepository {
    fun insertEquipmentData(equipmentData: EquipmentData)
    fun deleteEquipmentData(position: Int)
    fun getEquipmentData(position: Int): EquipmentData
    fun getEquipmentDataList(): ArrayList<EquipmentData>
}