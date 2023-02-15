package com.example.data.dao

import com.example.data.model.EquipmentEntity

interface EquipmentDao {
    // 희권이 firebase 완료되면 할 수 있을듯
    // @Query()
    fun getEquipmentData(position: Int): EquipmentEntity

    // @Insert()
    fun insertEquipmentData(equipmentData: EquipmentEntity)

    // @Delete()
    fun deleteEquipmentData(equipmentData: EquipmentEntity)
}