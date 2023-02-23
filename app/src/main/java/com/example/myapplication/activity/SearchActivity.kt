package com.example.myapplication.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
    }
}