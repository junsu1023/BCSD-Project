package com.example.domain.repository

import com.example.domain.model.EquipmentData

interface EquipmentRepository {
    fun insertEquipmentData(equipmentData: EquipmentData)
    fun deleteEquipmentData(equipmentData: EquipmentData)
    fun getEquipmentData(position: Int): EquipmentData
}