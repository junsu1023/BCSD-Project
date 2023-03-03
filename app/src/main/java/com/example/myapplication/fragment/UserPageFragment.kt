package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.domain.data.user.User
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.activity.SignInActivity
import com.example.myapplication.activity.UserPageActivity
import com.example.myapplication.databinding.SignUpActivityBinding
import com.example.myapplication.databinding.UserPageFragmentBinding
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPageFragment : Fragment() {
    private lateinit var binding: UserPageFragmentBinding
    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userdata: User
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_page_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userActivity = activity as UserPageActivity
        var uid = "null"

        arguments?.let { //액티비티에서 값을 받으면 넣음
            uid = it.getString("uid", "11")
        }

        auth = Firebase.auth //파이어 베이스 할당
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        //데이터 베이스 경로 저장
        databaseReference.child(uid).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val group: User? = snapshot.getValue(User::class.java)
                userdata = User(
                    idToken = group?.idToken.toString(),
                    emailId = group?.emailId.toString(),
                    password = group?.password.toString(),
                    name = group?.name.toString()
                )
                binding.user = userdata
            }

            override fun onCancelled(error: DatabaseError) {
                binding.user = User(
                    idToken = "Null",
                    emailId = "Null",
                    password = "Null",
                    name = "Null"
                )
            }
        } ) //여기는 사용자 정보를 가져오는 곳

        binding.signOutBtn.setOnClickListener{
            val intent = Intent(activity, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }

        binding.changePswBtn.setOnClickListener {
            if (binding.userCurrentPasswordInit.text.toString() == userdata.password) {
                userViewModel.changePsw(userdata, binding.userNewPasswordInit.text.toString())
                userViewModel.finishCheck.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        Toast.makeText(
                            activity, "비밀번호가 변경되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.userCurrentPasswordInit.text.clear()
                        binding.userNewPasswordInit.text.clear()
                    } else {
                        Toast.makeText(
                            activity, "비밀번호 변경을 실패하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
            else{
                Toast.makeText(
                    activity, "입력된 현재 비밀번호가 다릅니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.deleteUser.setOnClickListener{
            userActivity.toDeleteFragment(userdata.password, userdata.idToken)
        }
    }
}