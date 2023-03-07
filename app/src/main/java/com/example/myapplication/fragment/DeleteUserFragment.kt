package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.activity.SignInUpActivity
import com.example.myapplication.activity.UserPageActivity
import com.example.myapplication.databinding.DeleteUserFragmentBinding
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteUserFragment : Fragment() {
    private var _binding: DeleteUserFragmentBinding? = null
    val binding get() = _binding!!
    private var auth : FirebaseAuth? = null
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DeleteUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userActivity = activity as UserPageActivity

        lateinit var currentPassword: String
        lateinit var userUid: String
        auth = Firebase.auth //파이어 베이스 할당

        arguments?.let { //액티비티에서 값을 받으면 넣음
            currentPassword = it.getString("password", "null")
            userUid = it.getString("uid", "null")
        }


        binding.deleteAccountBtn.setOnClickListener {
            if (binding.deleteInputPsw.text.toString() == currentPassword) {
                userViewModel.deleteUser(userUid)
                userViewModel.finishCheck.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        Toast.makeText(
                            activity, "회원탈퇴가 완료되었습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(activity, SignInUpActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        auth?.signOut()
                    } else {
                        Toast.makeText(
                            activity, "회원탈퇴에 실패했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
            else{
                Toast.makeText(
                    activity, "입력된 비밀번호가 다릅니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}