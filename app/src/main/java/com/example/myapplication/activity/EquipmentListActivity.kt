package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.EquipmentData
import com.example.myapplication.R
import com.example.myapplication.adapter.EquipmentListAdapter
import com.example.myapplication.databinding.ActivityEquipmentListBinding
import com.example.myapplication.viewmodel.EquipmentListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EquipmentListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEquipmentListBinding
    private val equipmentListViewModel: EquipmentListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_list)

        binding.lifecycleOwner = this

        val searchWord = intent.getStringExtra("searchWord")

        val equipmentListAdapter = EquipmentListAdapter()

        val recyclerEquipmentList = mutableListOf<EquipmentData>()
        equipmentListViewModel.equipmentList.observe(this, Observer { equipmentList ->
            equipmentListAdapter.setData(equipmentList)
            if(searchWord != null) {
                recyclerEquipmentList.clear()
                for (equipment in equipmentList) {
                    if (equipment.name == searchWord) {
                        recyclerEquipmentList.add(equipment)
                    }
                    equipmentListAdapter.setData(recyclerEquipmentList)
                }
            }

            equipmentListAdapter.setOnLongClickListener {
                equipmentListViewModel.removeEquipmentData(equipmentList[it].name)
            }

            equipmentListAdapter.setOnClickListener {
                val intent = Intent(this, RentalActivity::class.java)
                intent.putExtra("equipmentName", equipmentList[it].name)
                intent.putExtra("currentCnt", equipmentList[it].currentCnt)
                intent.putExtra("albumUri", equipmentList[it].albumUri)
                intent.putExtra("totalCnt", equipmentList[it].totalCnt)

                startActivity(intent)
            }
        })

        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager(this).orientation
        )

        binding.equipmentRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = equipmentListAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }
}