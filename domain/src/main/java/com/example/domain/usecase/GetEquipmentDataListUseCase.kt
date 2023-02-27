package com.example.domain.usecase

import com.example.domain.model.EquipmentData
import com.example.domain.repository.EquipmentRepository

class GetEquipmentDataListUseCase(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(): ArrayList<EquipmentData> {
        return equipmentRepository.getEquipmentDataList()
    }
}