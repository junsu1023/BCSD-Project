package com.example.domain.usecase.firestore

import com.example.domain.repository.WarehouseRepository

class GetItemUseCase(
    private val warehouseRepository : WarehouseRepository
) {
    operator fun invoke() = warehouseRepository.getItem()
}