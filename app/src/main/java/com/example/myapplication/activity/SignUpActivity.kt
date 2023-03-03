package com.example.myapplication.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.domain.data.user.User
import com.example.myapplication.databinding.SignUpActivityBinding
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity(){
    private lateinit var binding: SignUpActivityBinding //바인딩할 xml 이름으로 수정
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.createAccountBtn.setOnClickListener {
            if (binding.userPsw.text.toString() == binding.userPswCheck.text.toString()) {
                userViewModel.signUpEmail(
                    binding.userId.text.toString(),
                    binding.userPsw.text.toString(),
                    binding.userName.text.toString()
                )
                userViewModel.finishCheck.observe(this, Observer {
                    if (it) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
}