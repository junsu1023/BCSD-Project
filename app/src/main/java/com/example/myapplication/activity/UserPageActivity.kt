package com.example.myapplication.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.domain.data.user.User
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.UserPageActivityBinding
import com.example.myapplication.fragment.DeleteUserFragment
import com.example.myapplication.fragment.UserPageFragment
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPageActivity  : AppCompatActivity() {
    private lateinit var binding: UserPageActivityBinding
    private val userPageFragment = UserPageFragment()
    private val deleteUserFragment = DeleteUserFragment()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserPageActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction().apply {
            val bundle = Bundle()
            bundle.putString("uid", intent.getStringExtra("uid").toString())
            userPageFragment.arguments = bundle
            add(R.id.container, userPageFragment)
            commit()
        }

    }
    fun toDeleteFragment(password: String, uid: String)
    {
        supportFragmentManager.beginTransaction().apply {
            val bundle = Bundle()
            bundle.putString("password", password)
            bundle.putString("uid", uid)
            deleteUserFragment.arguments = bundle
            replace(R.id.container, deleteUserFragment)
            commit()
        }
    }
}