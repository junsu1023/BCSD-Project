package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.EquipmentData
import com.example.domain.model.onSuccess
import com.example.domain.usecase.GetEquipmentDataListUseCase
import com.example.domain.usecase.GetEquipmentDataUseCase
import com.example.domain.usecase.InsertEquipmentUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RentalViewModel(
    private val getEquipmentDataListUseCase: GetEquipmentDataListUseCase,
    private val getEquipmentDataUseCase: GetEquipmentDataUseCase,
    private val insertEquipmentUseCase: InsertEquipmentUseCase,
    ): ViewModel() {
    private val _equipmentList = MutableLiveData<List<EquipmentData>>()
    val equipmentList: LiveData<List<EquipmentData>> get() = _equipmentList
    private var list = listOf<EquipmentData>()

    private var position = -1
    private lateinit var equipmentData: EquipmentData

    init {
        getEquipmentDataList()
    }

    fun setEquipmentData(equipmentName: String) {
        val searchEquipmentList = list.filter { it.name == equipmentName }
        _equipmentList.value = searchEquipmentList
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
}