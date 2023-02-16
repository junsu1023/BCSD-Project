package com.example.domain.usecase

import com.example.domain.model.EquipmentData
import com.example.domain.repository.EquipmentRepository

class GetEquipmentUseCase(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(position: Int): EquipmentData =
        equipmentRepository.getEquipmentData(position)
}