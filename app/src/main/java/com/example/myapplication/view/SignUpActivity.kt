package com.example.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class SignUpActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding //바인딩할 xml 이름으로 수정
    // 파이어베이스 인증을 위한 객체
    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // 바인딩할 xml 이름으로 수정
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth //파이어 베이스 할당

        binding.loginBtn.setOnClickListener{
            createAccount(binding.userId.text.toString(), binding.userPsw.text.toString()) //계정 생성 함수
        }
    }
    private fun createAccount(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) //이메일, 패스워드가 비어있지 않으면
        {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}