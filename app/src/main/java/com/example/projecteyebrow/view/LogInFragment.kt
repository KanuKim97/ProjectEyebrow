package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentLoginBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.viewModel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogInFragment : Fragment(), View.OnClickListener {
    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher
    @Inject lateinit var toastMessage: Toast

    private lateinit var userEmail: String
    private lateinit var userPassword: String

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val logInViewModel: LogInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        logInViewModel.isLogInSuccess.observe(viewLifecycleOwner) { result ->
            lifecycleScope.launch(mainDispatcher) {
                if (result.isSuccess) {
                    toProfileFragment()
                } else {
                    toastMessage.apply { setText(R.string.FailedLogIn) }.show()
                    clearEmailInputField()
                    clearPasswordInputField()
                }
            }
        }

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

    private fun clearEmailInputField(): Unit? = binding.EmailInput.text?.clear()

    private fun clearPasswordInputField(): Unit? = binding.PasswordInput.text?.clear()

    private fun toProfileFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, ProfileFragment())
        .addToBackStack("ProfileFragment")
        .commit()

    private fun toCreateAccountFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, SignInFragment())
        .addToBackStack("CreateAccountFragment")
        .commit()

    private fun toFindPasswordFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, FindPasswordFragment())
        .addToBackStack("FindPasswordFragment")
        .commit()

    private fun userLogIn(Email: String, Password: String): Job =
        logInViewModel.logInUserAccount(Email, Password)

    override fun onClick(view: View?) {
        userEmail = setUserEmail()
        userPassword = setUserPassword()

        when(view?.id) {
            R.id.LogIn_Btn -> userLogIn(userEmail, userPassword)
            R.id.CreateAccount_Btn -> toCreateAccountFragment()
            R.id.FindPassword_Btn -> toFindPasswordFragment()
        }
    }

}