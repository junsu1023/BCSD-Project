package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.data.user.User
import com.example.myapplication.R
import com.example.myapplication.databinding.UserPageActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class UserPageActivity  : AppCompatActivity() {
    private lateinit var binding: UserPageActivityBinding

    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userEmail: String
    private lateinit var userName: String
    private lateinit var curPsw: String

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
                userName = group?.name.toString()
                curPsw = group?.password.toString()
                userEmail = group?.emailId.toString()
                binding.welcomeMsg.text = userName + "님 환영합니다!" // 로그인한 계정의 이름을 출력
            }

            override fun onCancelled(error: DatabaseError) {
                binding.welcomeMsg.text = "알수없음"
            }
        } )

        binding.signOutBtn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }

        binding.changePswBtn.setOnClickListener{
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.change_psw_dialog, null)
            val builder = AlertDialog.Builder(this)
                .setTitle("비밀번호 변경")
                .setView(rootView)
                .setPositiveButton("예") { _, _ ->
                    val chanePsw: TextView = rootView.findViewById(R.id.change_psw)
                    val chanePswCheck: TextView = rootView.findViewById(R.id.change_psw_check)
                    changePassword(chanePsw.text.toString(), chanePswCheck.text.toString())
                }
                .setNegativeButton("취소") { _, _ -> null}
                .show()
        } //비밀번호 변경 버튼을 누르면 다이얼그램을 이용

        binding.deleteUser.setOnClickListener{
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.delete_user_dialog, null)
            val builder = AlertDialog.Builder(this)
                .setTitle("회원탈퇴")
                .setView(rootView)
                .setPositiveButton("예") { _, _ ->
                    val checkPsw: TextView = rootView.findViewById(R.id.delete_input_psw)
                    deleteUser(checkPsw.text.toString())
                }
                .setNegativeButton("취소") { _, _ -> null}
                .show()
        }
    }

    private fun changePassword(new_password: String, new_password_check: String){
        if(new_password == new_password_check)
        {
            auth?.currentUser?.updatePassword(new_password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        var userInfo: User = User(
                            idToken = intent.getStringExtra("uid").toString(),
                            emailId = userEmail,
                            password = new_password,
                            name = userName
                        )
                        databaseReference.child(intent.getStringExtra("uid").toString()).setValue(userInfo)

                        Toast.makeText(
                            baseContext, "비밀번호가 변경되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        else{
            Toast.makeText(
                baseContext, "비밀번호가 다릅니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun deleteUser(password: String){
        if(password == curPsw)
        {
            auth?.currentUser?.delete()?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    databaseReference.child(intent.getStringExtra("uid").toString()).removeValue()

                    Toast.makeText(
                        baseContext, "회원탈퇴가 완료되었습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        else{
            Toast.makeText(
                baseContext, "비밀번호가 다릅니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}