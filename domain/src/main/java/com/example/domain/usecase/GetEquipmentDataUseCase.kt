package com.example.domain.usecase

import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import com.example.domain.repository.EquipmentRepository
import kotlinx.coroutines.flow.Flow

class GetEquipmentDataUseCase(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(equipmentName: String): Flow<ResponseData<MutableMap<String, Any>?>> {
        return equipmentRepository.getEquipmentData(equipmentName)
    }
}