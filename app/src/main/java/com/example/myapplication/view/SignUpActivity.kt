package com.example.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.data.user.User
import com.example.myapplication.databinding.SignUpActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity(){
    private lateinit var binding: SignUpActivityBinding //바인딩할 xml 이름으로 수정
    // 파이어베이스 인증을 위한 객체
    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userInfo: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth //파이어 베이스 할당
        databaseReference = FirebaseDatabase.getInstance().getReference("User")

        binding.loginBtn.setOnClickListener{
            if(binding.userPsw.text.toString() == binding.userPswCheck.text.toString()) {
                createAccount(
                    binding.userId.text.toString(),
                    binding.userPsw.text.toString(),
                    binding.userName.text.toString()
                ) //계정 생성 함수
            }
            else
            {
                Toast.makeText(
                    this, "비밀번호가 다릅니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun createAccount(email: String, password: String, name: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        /*auth?.currentUser
                            ?.sendEmailVerification()?.addOnCompleteListener { task ->
                                if (task.isSuccessful)
                                {
                                    Toast.makeText(
                                        this, "계정 생성 완료.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    auth?.signOut()
                                    finish() // 가입창 종료
                                }
                        }*/
                        val firebaseUser = FirebaseAuth.getInstance().currentUser

                        userInfo = User(
                            idToken = firebaseUser?.uid.toString(),
                            emailId = firebaseUser?.email.toString(),
                            password = password,
                            name = name
                        )

                        databaseReference.child(firebaseUser?.uid.toString()).setValue(userInfo)
                        //파이어베이스 데이터베이스에 값을 저장

                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // 가입창 종료
                    }
                    else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}