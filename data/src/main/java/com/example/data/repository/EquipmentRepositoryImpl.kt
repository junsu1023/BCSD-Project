package com.example.data.repository

import com.example.data.mapper.mapToEntity
import com.example.data.datasource.WarehouseDataSource
import com.example.domain.model.EquipmentData
import com.example.domain.model.ResponseData
import com.example.domain.repository.EquipmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class EquipmentRepositoryImpl(private val warehouseDataSource: WarehouseDataSource): EquipmentRepository {
    override fun insertEquipmentData(equipmentData: EquipmentData) {
        warehouseDataSource.addItem(equipmentData)
    }

    override fun deleteEquipmentData(equipmentName: String) {
        warehouseDataSource.deleteItem(equipmentName)
    }

    override fun getEquipmentListData(): Flow<ResponseData<List<EquipmentData>>> {
        val data = warehouseDataSource.getItem()
        return data
    }
}