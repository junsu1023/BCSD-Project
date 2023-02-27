package com.example.data.mapper

import com.example.data.model.EquipmentEntity
import com.example.domain.model.EquipmentData

fun EquipmentEntity.mapToData(): EquipmentData {
    return EquipmentData(
        albumUri, name, totalCnt, currentCnt
    )
}

fun EquipmentData.mapToEntity(): EquipmentEntity {
    return EquipmentEntity(
        albumUri, name, totalCnt, currentCnt
    )
}