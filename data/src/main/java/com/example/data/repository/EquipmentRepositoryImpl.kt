package com.example.data.repository

import com.example.data.dao.EquipmentDao
import com.example.data.mapper.mapToData
import com.example.data.mapper.mapToEntity
import com.example.data.data.EquipmentData.equipmentData
import com.example.domain.model.EquipmentData
import com.example.domain.repository.EquipmentRepository

class EquipmentRepositoryImpl(private val equipmentDao: EquipmentDao): EquipmentRepository {
    override fun insertEquipmentData(equipmentData: EquipmentData) {
        equipmentDao.insertEquipmentData(equipmentData.mapToEntity())
    }

    override fun deleteEquipmentData(position: Int) {
        equipmentDao.deleteEquipmentData(position)
    }

    override fun getEquipmentData(position: Int): EquipmentData {
        val data = equipmentDao.getEquipmentData(position)
        return data.mapToData()
    }

    override fun getEquipmentDataList(): ArrayList<EquipmentData> {
        val mapperEquipmentData = ArrayList<EquipmentData>()
        for(equipment in equipmentData) {
            mapperEquipmentData.add(equipment.mapToData())
        }

        return mapperEquipmentData
    }
}