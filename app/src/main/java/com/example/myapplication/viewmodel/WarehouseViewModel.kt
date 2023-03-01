package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.firestore.AddItemUseCase
import com.example.domain.usecase.firestore.DeleteItemUseCase
import com.example.domain.usecase.firestore.GetItemUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WarehouseViewModel(
    private val addItemUseCase: AddItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val getItemUseCase: GetItemUseCase
): ViewModel() {

    init {
        getItem()
    }
    
    private fun getItem() = viewModelScope.launch {
        getItemUseCase().collect()
    }

    fun addItem(
        albumUri: String?,
        name : String,
        totalCnt: Int,
        currentCnt: Int
    ) = viewModelScope.launch {

    }

    fun deleteITem() = viewModelScope.launch {

    }
}