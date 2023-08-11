package com.example.projecteyebrow.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentSigninBinding
import com.example.projecteyebrow.view.signIn.SignInSection
import com.example.projecteyebrow.view.signIn.SignInTitleSection
import com.example.projecteyebrow.viewModel.CreateAccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentSigninBinding? = null
    private val binding: FragmentSigninBinding get() = _binding!!
    private val createAccountViewModel: CreateAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.CreateAccountTitleSection.setContent {
            MaterialTheme {
                SignInTitleSection()
            }
        }

        binding.CreateAccountSection.setContent {
            MaterialTheme {
                SignInSection(
                    createAccountClicked = { /*TODO*/ },
                    toLogInPageClicked = {
                        requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.FragmentContainer, LogInFragment())
                        .commit()
                    }
                )
            }
        }

        createAccountViewModel.isCreateSuccess.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                toProfileFragment()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun toProfileFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, ProfileFragment())
        .commit()

//    private fun createUserAccount(
//        Email: String,
//        Password: String,
//        NickName: String
//    ): Job = createAccountViewModel.createUserAccount(Email, Password, NickName)

//    private fun validateUserInput(
//        Email: String,
//        NickName: String,
//        Password: String,
//        ConfirmPassword: String
//    ) {
//        when {
//            !Patterns.EMAIL_ADDRESS.matcher(Email).matches() -> {
//                toastMessage.apply { setText("이메일 형식이 아닙니다. 다시 입력해주세요.") }.show()
//                clearEmailInputField()
//            }
//            NickName.length >= 15 -> {
//                toastMessage.apply { setText("닉네임이 너무 깁니다!") }.show()
//                clearNickNameInputField()
//            }
//            Password != ConfirmPassword -> {
//                toastMessage.apply { setText("비밀번호가 일치하지 않습니다.") }.show()
//                clearConfirmPasswordInputField()
//            }
//            else -> createUserAccount(Email, Password, NickName)
//        }
//    }
//
//    override fun onClick(view: View?) {
//        userEmail = setUserEmail()
//        userNickName = setUserNickName()
//        userPassword = setUserPassword()
//        confirmPassword = setConfirmPassword()
//
//        when (view?.id) {
//            R.id.toLogInFragment_Btn -> toLogInFragment()
//            R.id.CreateAccount_Btn ->
//                validateUserInput(userEmail, userNickName, userPassword, confirmPassword)
//        }
//    }

}