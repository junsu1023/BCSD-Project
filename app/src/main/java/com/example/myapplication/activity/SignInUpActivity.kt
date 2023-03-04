package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.SignInUpActivityBinding
import com.example.myapplication.fragment.SignInFragment
import com.example.myapplication.fragment.SignUpFragment

class SignInUpActivity : AppCompatActivity(){
    private lateinit var binding: SignInUpActivityBinding
    private val signInFragment = SignInFragment()
    private val signUpFragment = SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInUpActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, signInFragment)
            commit()
        }

    }
    fun toSignInFragment()
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, signInFragment)
            commit()
        }
    }

    fun toSignUpFragment()
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, signUpFragment)
            commit()
        }
    }

    fun toSelectButtonActivity(uid: String){
        val intent = Intent(this, SelectButtonActivity::class.java)
        intent.putExtra("uid", uid)
        startActivity(intent)
        finish()
    }
}