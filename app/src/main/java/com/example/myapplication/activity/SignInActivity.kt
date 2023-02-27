package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.SignInActivityBinding
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity(){
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var binding: SignInActivityBinding //바인딩할 xml 이름으로 수정
    // 파이어베이스 인증을 위한 객체
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.signUpBtn.setOnClickListener{
            val intent_main = Intent(this, SignUpActivity::class.java)
            startActivity(intent_main)
        } // 회원가입 버튼

        binding.signInBtn.setOnClickListener{
            userViewModel.signInEmail(binding.userId.text.toString(), binding.userPsw.text.toString())
            userViewModel.finishCheck.observe(this, Observer {
                if(it) {
                    Toast.makeText(
                        baseContext, "로그인에 성공 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, UserPageActivity::class.java)
                    intent.putExtra("uid", auth?.currentUser?.uid)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(
                        baseContext, "로그인에 실패 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } // 로그인 버튼
    }
}