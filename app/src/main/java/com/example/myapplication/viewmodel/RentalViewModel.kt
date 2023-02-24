package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.EquipmentData
import com.example.domain.usecase.DeleteEquipmentUseCase
import com.example.domain.usecase.GetEquipmentUseCase
import com.example.domain.usecase.InsertEquipmentUseCase

class RentalViewModel(
    private val getEquipmentUseCase: GetEquipmentUseCase,
    private val insertEquipmentUseCase: InsertEquipmentUseCase,
    ): ViewModel() {
    private val _equipment = MutableLiveData<EquipmentData>()
    val equipment: LiveData<EquipmentData> get() = _equipment

    private var position = -1
    private lateinit var equipmentData: EquipmentData

    fun setEquipmentData(position: Int) {
        this.position = position
        equipmentData = if(position == -1) {
            EquipmentData(null, "", 0, 0)
        }
        else {
            getEquipmentUseCase(position)
        }
    }

    fun setImageUri(newImageUri: String?) {
        equipmentData = equipmentData.copy(albumUri = newImageUri)
        _equipment.value = equipmentData
    }

    fun insertEquipmentData(equipmentData: EquipmentData) {
        insertEquipmentUseCase(equipmentData)
    }

    fun getEquipmentData(position: Int) {
        getEquipmentUseCase(position)
    }
}