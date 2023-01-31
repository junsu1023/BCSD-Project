package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SignInActivityBinding

class SignInActivity : AppCompatActivity(){
    private lateinit var binding: SignInActivityBinding //바인딩할 xml 이름으로 수정
    // 파이어베이스 인증을 위한 객체
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.signInBtn.setOnClickListener{
            val intent_main = Intent(this, SignInActivity::class.java)
            startActivity(intent_main)
        } // 회원가입 버튼

        binding.loginBtn.setOnClickListener{
            signIn(binding.userId.text.toString(), binding.userPsw.text.toString())
        } // 로그인 버튼
    }

    private fun signIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser) // 회원가입 성공하면 액티비티 이동
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    // 유저정보 넘겨주고 메인 액티비티 호출
    private fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            val intent = Intent(this, LoginActivity::class.java) // 수정 필요
            //우선은 임시 로그인 완료 액티비티로 넘어가게 구현
            startActivity(intent)
            finish()
        }
    }
}