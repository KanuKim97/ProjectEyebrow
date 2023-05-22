package com.example.projecteyebrow.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentSigninBinding
import com.example.projecteyebrow.viewModel.CreateAccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!
    private val createAccountViewModel: CreateAccountViewModel by viewModels()

    private val userEmail: String by lazy { setUserEmail() }
    private val userNickName: String by lazy { setUserNickName() }
    private val userPassword: String by lazy { setUserPassword() }
    private val confirmPassword: String by lazy { setConfirmPassword() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isCreateAccountSuccess()
        binding.CreateAccountBtn.setOnClickListener(this)
        binding.toLogInFragmentBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserEmail(): String = binding.EmailInput.text.toString()

    private fun setUserNickName(): String = binding.NameInput.text.toString()

    private fun setUserPassword(): String = binding.PasswordInput.text.toString()

    private fun setConfirmPassword(): String = binding.ConfirmPasswordInput.text.toString()

    private fun clearEmailInputField(): Unit? = binding.EmailInput.text?.clear()

    private fun clearNickNameInputField(): Unit? = binding.NameInput.text?.clear()

    private fun clearConfirmPasswordInputField(): Unit? = binding.ConfirmPasswordInput.text?.clear()

    private fun toLogInFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, LogInFragment())
        .commit()

    private fun toProfileFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, ProfileFragment())
        .commit()

    private fun createUserAccount(
        Email: String,
        Password: String,
        NickName: String
    ): Job = createAccountViewModel.createUserAccount(Email, Password, NickName)

    private fun isCreateAccountSuccess(): Unit =
        createAccountViewModel.isCreateSuccess.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) { toProfileFragment() }
        }

    private fun validateUserInput(
        Email: String,
        NickName: String,
        Password: String,
        ConfirmPassword: String
    ) {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(Email).matches() -> {
                toastMessage.apply { setText("이메일 형식이 아닙니다. 다시 입력해주세요.") }.show()
                clearEmailInputField()
            }
            NickName.length >= 15 -> {
                toastMessage.apply { setText("닉네임이 너무 깁니다!") }.show()
                clearNickNameInputField()
            }
            Password != ConfirmPassword -> {
                toastMessage.apply { setText("비밀번호가 일치하지 않습니다.") }.show()
                clearConfirmPasswordInputField()
            }
            else -> createUserAccount(Email, Password, NickName)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toLogInFragment_Btn -> toLogInFragment()
            R.id.CreateAccount_Btn ->
                validateUserInput(userEmail, userNickName, userPassword, confirmPassword)
        }
    }

}