package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.activity.SelectButtonActivity
import com.example.myapplication.activity.SignInUpActivity
import com.example.myapplication.databinding.SignInFragmentBinding
import com.example.myapplication.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment: Fragment() {
    private var _binding: SignInFragmentBinding? = null
    val binding get() = _binding!!
    private val userViewModel: UserViewModel by viewModel()
    private var auth : FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInUpActivity = activity as SignInUpActivity

        auth = Firebase.auth

        binding.signUpBtn.setOnClickListener{
            signInUpActivity.toSignUpFragment()
        }

        binding.signInBtn.setOnClickListener{
            userViewModel.signInEmail(binding.userId.text.toString(), binding.userPsw.text.toString())
            userViewModel.finishCheck.observe(viewLifecycleOwner, Observer {
                if(it) {
                    Toast.makeText(
                        activity, "로그인에 성공 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    signInUpActivity.toSelectButtonActivity(auth?.currentUser?.uid.toString())
                }
                else{
                    Toast.makeText(
                        activity, "로그인에 실패 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } // 로그인 버튼

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}