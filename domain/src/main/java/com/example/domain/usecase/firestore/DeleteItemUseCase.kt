package com.example.domain.usecase.firestore

import com.example.domain.repository.WarehouseRepository

class DeleteItemUseCase(
    private val warehouseRepository : WarehouseRepository
) {
    suspend operator fun invoke(
        name: String
    ) = warehouseRepository.deleteItem(name)
}