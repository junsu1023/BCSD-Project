package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.EquipmentData
import com.example.domain.model.onSuccess
import com.example.domain.usecase.DeleteEquipmentUseCase
import com.example.domain.usecase.GetEquipmentDataListUseCase
import com.example.domain.usecase.InsertEquipmentUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EquipmentListViewModel (
    private val getEquipmentDataListUseCase: GetEquipmentDataListUseCase,
    private val deleteEquipmentUseCase: DeleteEquipmentUseCase,
    private val insertEquipmentUseCase: InsertEquipmentUseCase)
: ViewModel(){
    private val _equipmentList = MutableLiveData<List<EquipmentData>>()
    val equipmentList: LiveData<List<EquipmentData>> get() = _equipmentList
    private var list = listOf<EquipmentData>()

    init {
        getEquipmentDataList()
    }

    fun removeEquipmentData(name : String) {
        viewModelScope.launch {
            deleteEquipmentUseCase(name)
        }
    }

    fun getEquipmentDataList() {
        viewModelScope.launch {
            getEquipmentDataListUseCase().collectLatest {
                it.onSuccess {
                    _equipmentList.value = it
                    list = it
                }
            }
        }
    }

    fun searchEquipment(equipmentName: String) {
        val searchEquipmentList = list.filter { it.name == equipmentName }
        _equipmentList.value = searchEquipmentList
    }
}