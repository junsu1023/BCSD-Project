package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.domain.model.EquipmentData
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRentalEquipmentBinding
import com.example.myapplication.viewmodel.EquipmentListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentalActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRentalEquipmentBinding
    private val rentalViewModel: EquipmentListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rental_equipment)

        binding.rentalActivity = this
        binding.lifecycleOwner = this

        val equipmentName = intent.getStringExtra("equipmentName")
        val albumUri = intent.getStringExtra("albumUri")
        val currentCnt = intent.getIntExtra("currentCnt", -1)
        val totalCnt = intent.getIntExtra("totalCnt", -1)
        binding.equipmentNameTextView.text = equipmentName
        binding.currentNumTextView.text = intent.getIntExtra("currentCnt", -1).toString()


        binding.rentalButton.setOnClickListener {
            val intent = Intent(this, EquipmentListActivity::class.java)
            if(currentCnt - 1 < 0) {
                Toast.makeText(this, "대여할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                rentalViewModel.insertEquipmentData(EquipmentData(albumUri,equipmentName!!,totalCnt,currentCnt-1))
            }
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
            rentalViewModel.removeEquipmentData(equipmentName!!)
            val intent = Intent(this, EquipmentListActivity::class.java)
            startActivity(intent)
        }
        setResult(RESULT_OK, intent)
    }
}