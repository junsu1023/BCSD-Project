package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SignInActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

        binding.signUpBtn.setOnClickListener{
            val intent_main = Intent(this, SignInActivity::class.java)
            startActivity(intent_main)
        } // 회원가입 버튼

        binding.signInBtn.setOnClickListener{
            signInWithEmail(binding.userId.text.toString(), binding.userPsw.text.toString())
        } // 로그인 버튼
    }

    private fun signInWithEmail(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveUserPage(auth?.currentUser) // 회원가입 성공하면 액티비티 이동
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
    private fun moveUserPage(user: FirebaseUser?){
        if( user!= null){
            val intent = Intent(this, UserPageActivity::class.java) // 임시 유저페이지로  이동
            intent.putExtra("uid", user.uid)
            startActivity(intent)
            finish()
        }
    }
}