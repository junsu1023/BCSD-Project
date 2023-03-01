package com.example.domain.usecase.firestore

import com.example.domain.repository.EquipmentRepository

class GetItemUseCase(
    private val equipmentRepository : EquipmentRepository
) {
    operator fun invoke() = equipmentRepository.getEquipmentListData()
}