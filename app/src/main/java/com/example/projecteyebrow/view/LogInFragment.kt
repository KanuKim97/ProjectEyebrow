package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentLoginBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.view.logIn.LogInBtnSection
import com.example.projecteyebrow.view.logIn.LogInTitleSection
import com.example.projecteyebrow.viewModel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogInFragment : Fragment() {
    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher
    @Inject lateinit var toastMessage: Toast

    private val userEmail: String by lazy { setUserEmail() }
    private val userPassword: String by lazy { setUserPassword() }

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
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
        binding.TitleSection.setContent {
            MaterialTheme {
                LogInTitleSection()
            }
        }

        binding.BtnSection.setContent {
            MaterialTheme {
                LogInBtnSection(
                    toFindPasswordBtnClick = {
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.FragmentContainer, FindPasswordFragment())
                            .commit()
                    },
                    userLogInBtnClick = {
                        logInViewModel.logInUserAccount(userEmail, userPassword)
                    },
                    toCreateAccountBtnClick = {
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.FragmentContainer, SignInFragment())
                            .commit()
                    }
                )
            }
        }

        logInViewModel.isLogInSuccess.observe(viewLifecycleOwner) { result ->
            lifecycleScope.launch(mainDispatcher) {
                if (result.isSuccess) {
                    toProfileFragment()
                } else {
                    toastMessage.apply { setText("로그인에 실패하였습니다.") }.show()
                    clearEmailInputField()
                    clearPasswordInputField()
                }
            }
        }
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
        .commit()
}