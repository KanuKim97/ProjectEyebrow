package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentLoginBinding
import com.example.projecteyebrow.viewModel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class LogInFragment : Fragment(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val logInViewModel: LogInViewModel by viewModels()

    private val userEmail: String by lazy { setUserEmail() }
    private val userPassword: String by lazy { setUserPassword() }

    private val profileFragment: ProfileFragment = ProfileFragment()
    private val createAccountFragment: SignInFragment = SignInFragment()
    private val findPasswordFragment: FindPasswordFragment = FindPasswordFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isLogInSuccess()
        binding.LogInBtn.setOnClickListener(this)
        binding.FindPasswordBtn.setOnClickListener(this)
        binding.CreateAccountBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserEmail(): String = binding.EmailInput.text.toString()

    private fun setUserPassword(): String = binding.PasswordInput.text.toString()

    private fun userLogIn(Email: String, Password: String): Job =
        logInViewModel.logInUserAccount(Email, Password)

    private fun isLogInSuccess(): Unit =
        logInViewModel.isLogInSuccess.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                toProfileFragment()
            } else {
                toastMessage.apply {
                    setText(R.string.FailedLogIn)
                    duration = Toast.LENGTH_SHORT
                }.show()

                binding.EmailInput.text?.clear()
                binding.PasswordInput.text?.clear()
            }
        }

    private fun toProfileFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, profileFragment)
        .addToBackStack("ProfileFragment")
        .commit()

    private fun toCreateAccountFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, createAccountFragment)
        .addToBackStack("CreateAccountFragment")
        .commit()

    private fun toFindPasswordFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, findPasswordFragment)
        .addToBackStack("FindPasswordFragment")
        .commit()

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.LogIn_Btn -> userLogIn(userEmail, userPassword)
            R.id.CreateAccount_Btn -> toCreateAccountFragment()
            R.id.FindPassword_Btn -> toFindPasswordFragment()
        }
    }

}