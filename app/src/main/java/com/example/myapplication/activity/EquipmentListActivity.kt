package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
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
        val borrowCnt = intent.getIntExtra("borrowCnt", -1)
        val itemName = intent.getStringExtra("itemName")
        if(!itemName.isNullOrEmpty()) {

        }

        val equipmentListAdapter = EquipmentListAdapter()

        val recyclerEquipmentList = mutableListOf<EquipmentData>()
        equipmentListViewModel.equipmentList.observe(this, Observer { equipmentList ->
            equipmentListAdapter.setData(equipmentList)
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

        equipmentListAdapter.setOnClickListener {
            val intent = Intent(this, RentalActivity::class.java)
            intent.putExtra("position", it)
            startActivity(intent)
        }

        equipmentListAdapter.setOnLongClickListener {
            equipmentListViewModel.removeEquipmentData(it)
        }
    }
}