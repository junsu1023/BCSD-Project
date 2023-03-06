package com.example.domain.usecase

import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import com.example.domain.repository.EquipmentRepository
import kotlinx.coroutines.flow.Flow

class InsertEquipmentUseCase(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(equipmentData: EquipmentData){
        return equipmentRepository.insertEquipmentData(equipmentData)
    }
}