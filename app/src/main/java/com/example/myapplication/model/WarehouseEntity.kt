package com.example.myapplication.model

data class WarehouseEntity(
    var name: String,
    var rentalUser : String,
    var totalItem: Int,
    var currentItem: Int,
    var rentalState: Boolean
)
{
    fun toMap(): HashMap<String, Any?> {
        return hashMapOf(
            "rentalUser" to rentalUser,
            "totalItem" to totalItem,
            "currentItem" to currentItem,
            "rentalState" to rentalState
        )
    }
}