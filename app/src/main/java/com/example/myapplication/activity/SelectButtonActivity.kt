package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.adapter.EquipmentListAdapter
import com.example.myapplication.databinding.ActivitySelectButtonBinding

class SelectButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectButtonBinding
    private lateinit var changeActivity: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_button)

        val mainRentalButton: Button = binding.mainRentalButton
        val mainUpdateButton: Button = binding.mainUpdateButton
        val mainSearchButton: Button = binding.mainSearchButton

        mainRentalButton.setOnClickListener {
            onMainRentalButtonClick()
        }

        mainUpdateButton.setOnClickListener {
            onMainUpdateButtonClick()
        }

        mainSearchButton.setOnClickListener {
            onMainSearchButtonClick()
        }
    }

    private fun onMainRentalButtonClick() {
        changeActivity = Intent(this, EquipmentListActivity::class.java)
        startActivity(changeActivity)
    }

    private fun onMainUpdateButtonClick() {
        changeActivity = Intent(this, UpdateActivity::class.java)
        startActivity(changeActivity)
    }

    private fun onMainSearchButtonClick() {
        changeActivity = Intent(this, SearchActivity::class.java)
        startActivity(changeActivity)
    }
}