package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.activity.SignInUpActivity
import com.example.myapplication.databinding.SignUpFragmentBinding
import com.example.myapplication.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment :Fragment() {
    private var _binding: SignUpFragmentBinding? = null
    val binding get() = _binding!!
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInUpActivity = activity as SignInUpActivity

        binding.createAccountBtn.setOnClickListener {
            if (binding.userPsw.text.toString() == binding.userPswCheck.text.toString()) {
                userViewModel.signUpEmail(
                    binding.userId.text.toString(),
                    binding.userPsw.text.toString(),
                    binding.userName.text.toString()
                )
                userViewModel.finishCheck.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        Toast.makeText(
                            activity, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        signInUpActivity.toSignInFragment()

                    } else {
                        Toast.makeText(
                            activity, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}