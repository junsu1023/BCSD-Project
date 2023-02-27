package com.example.domain.usecase

import com.example.domain.model.EquipmentData
import com.example.domain.repository.EquipmentRepository

class DeleteEquipmentUseCase(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(position: Int) {
        equipmentRepository.deleteEquipmentData(position)
    }
}