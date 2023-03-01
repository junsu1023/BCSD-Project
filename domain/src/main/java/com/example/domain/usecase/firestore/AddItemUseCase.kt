package com.example.domain.usecase.firestore

import com.example.domain.repository.WarehouseRepository

class AddItemUseCase(
    private val warehouseRepository : WarehouseRepository
) {
    suspend operator fun invoke(
        albumUri: String?,
        name: String,
        totalCnt: Int,
        currentCnt: Int
    ) = warehouseRepository.addItem(albumUri, name, totalCnt, currentCnt)
}