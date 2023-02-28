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
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPageActivity  : AppCompatActivity() {
    private lateinit var binding: UserPageActivityBinding
    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: User
    private val userViewModel: UserViewModel by viewModel()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserPageActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth //파이어 베이스 할당
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        //데이터 베이스 경로 저장
        databaseReference.child(intent.getStringExtra("uid").toString()).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val group: User? = snapshot.getValue(User::class.java)
                user = User(
                    idToken = group?.idToken.toString(),
                    emailId = group?.emailId.toString(),
                    password = group?.password.toString(),
                    name = group?.name.toString()
                )
                binding.userNamePrint.text = user.name
                binding.userEmailPrint.text = user.emailId
            }

            override fun onCancelled(error: DatabaseError) {
                binding.userNamePrint.text = "None"
                binding.userEmailPrint.text = "None"
            }
        } ) //여기는 사용자 정보를 가져오는 곳

        binding.signOutBtn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }

        binding.changePswBtn.setOnClickListener {
            if (binding.userCurrentPasswordInit.text.toString() == user.password) {
                userViewModel.changePsw(user, binding.userNewPasswordInit.text.toString())
                userViewModel.finishCheck.observe(this, Observer {
                    if (it) {
                        Toast.makeText(
                            baseContext, "비밀번호가 변경되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.userCurrentPasswordInit.text.clear()
                        binding.userNewPasswordInit.text.clear()
                    } else {
                        Toast.makeText(
                            baseContext, "비밀번호 변경을 실패하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
            else{
                Toast.makeText(
                    baseContext, "입력된 현재 비밀번호가 다릅니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.deleteUser.setOnClickListener{
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.delete_user_dialog, null)
            val builder = AlertDialog.Builder(this)
                .setTitle("회원탈퇴")
                .setView(rootView)
                .setPositiveButton("예") { _, _ ->
                    val checkPsw: TextView = rootView.findViewById(R.id.delete_input_psw)
                    if(user.password == checkPsw.text.toString()) {
                        userViewModel.deleteUser(user.idToken)
                        userViewModel.finishCheck.observe(this, Observer {
                            if(it){
                                Toast.makeText(
                                    baseContext, "회원탈퇴가 완료되었습니다",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(
                                    baseContext, "회원탈퇴에 실패했습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                    else{
                        Toast.makeText(
                            baseContext, "입력된 비밀번호가 현재 비밀번호와 다릅니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .setNegativeButton("취소") { _, _ -> null}
                .show()
        }
    }
}