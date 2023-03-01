package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRentalEquipmentBinding
import com.example.myapplication.viewmodel.RentalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentalActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRentalEquipmentBinding
    private val rentalViewModel: RentalViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rental_equipment)

        rentalViewModel.setEquipmentData(intent.getIntExtra("position", -1))

        binding.rentalActivity = this
        binding.lifecycleOwner = this

        val borrowCnt = binding.borrowNumTextView.text
        binding.rentalButton.setOnClickListener {
            if(borrowCnt.isBlank()) {
                Toast.makeText(applicationContext, "대여 갯수를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, EquipmentListActivity::class.java)
            intent.putExtra("borrowCnt", binding.borrowNumTextView.text.toString().toInt())
        }

        val intent = Intent(this, EquipmentListActivity::class.java)
        setResult(RESULT_OK, intent)
    }
}