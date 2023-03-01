package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        val searchButton = binding.searchActivitySearchButton
        val contentTextView = binding.searchContentTextView

        searchButton.setOnClickListener {
            if(contentTextView.text.isNotBlank()) {
                val intent = Intent(this, EquipmentListActivity::class.java)
                intent.putExtra("searchWord", contentTextView.text.toString())
                startActivity(intent)
            }
        }
    }
}