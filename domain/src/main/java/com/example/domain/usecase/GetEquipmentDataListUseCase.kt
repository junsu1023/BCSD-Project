package com.example.domain.usecase

import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import com.example.domain.repository.EquipmentRepository
import kotlinx.coroutines.flow.Flow

class GetEquipmentDataListUseCase(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(): Flow<ResponseData<List<EquipmentData>>> {
        return equipmentRepository.getEquipmentListData()
    }
}