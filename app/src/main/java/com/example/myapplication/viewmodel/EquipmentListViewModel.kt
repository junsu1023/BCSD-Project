package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.EquipmentData
import com.example.domain.usecase.DeleteEquipmentUseCase
import com.example.domain.usecase.GetEquipmentDataListUseCase
import com.example.domain.usecase.GetEquipmentUseCase

class EquipmentListViewModel (
    private val getEquipmentDataListUseCase: GetEquipmentDataListUseCase,
    private val deleteEquipmentUseCase: DeleteEquipmentUseCase)
: ViewModel(){
    private val _equipmentList = MutableLiveData<ArrayList<EquipmentData>>()
    val equipmentList: LiveData<ArrayList<EquipmentData>> get() = _equipmentList

    init {
        _equipmentList.value = getEquipmentDataListUseCase()
    }

    fun removeEquipmentData(position: Int) {
        deleteEquipmentUseCase(position)
    }
}