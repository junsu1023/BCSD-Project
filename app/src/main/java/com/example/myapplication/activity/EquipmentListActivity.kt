package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.EquipmentListAdapter
import com.example.myapplication.databinding.ActivityEquipmentListBinding
import com.example.myapplication.viewmodel.EquipmentListViewModel
import com.example.myapplication.viewmodel.ImageViewModel

class EquipmentListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEquipmentListBinding
    private val equipmentListAdapter = EquipmentListAdapter()
    private val equipmentListViewModel: EquipmentListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_list)

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