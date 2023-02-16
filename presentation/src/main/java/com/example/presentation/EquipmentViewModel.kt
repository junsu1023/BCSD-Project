package com.example.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.model.EquipmentData
import com.example.domain.usecase.DeleteEquipmentUseCase
import com.example.domain.usecase.GetEquipmentUseCase
import com.example.domain.usecase.InsertEquipmentUseCase

class EquipmentViewModel(
    private val getEquipmentUseCase: GetEquipmentUseCase,
    private val insertEquipmentUseCase: InsertEquipmentUseCase,
    private val deleteEquipmentUseCase: DeleteEquipmentUseCase
    ): ViewModel() {
    fun insertEquipmentData(equipmentData: EquipmentData) {
        insertEquipmentUseCase(equipmentData)
    }

    fun deleteEquipmentData(equipmentData: EquipmentData) {
        deleteEquipmentUseCase(equipmentData)
    }

    fun getEquipmentData(position: Int) {
        getEquipmentUseCase(position)
    }
}