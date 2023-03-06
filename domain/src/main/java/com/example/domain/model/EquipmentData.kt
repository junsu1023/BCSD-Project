package com.example.domain.model

data class EquipmentData(
    val albumUri: String? = "",
    val name: String = "",
    val totalCnt: Int = 0,
    var currentCnt: Int = 0
)
