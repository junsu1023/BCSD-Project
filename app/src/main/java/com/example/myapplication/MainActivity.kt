package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.WarehouseRepositoryImpl
import com.example.myapplication.model.ResponseEntity
import com.example.myapplication.model.WarehouseEntity
import com.example.myapplication.model.onFailure
import com.example.myapplication.model.onSuccess
import com.example.myapplication.repository.WarehouseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = WarehouseRepositoryImpl().getItem()

        CoroutineScope(Dispatchers.Main).launch {
            items.collectLatest {
                it.onSuccess {

                }.onFailure {

                }
            }
        }
    }
}