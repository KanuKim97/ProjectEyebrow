package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentFindPasswordBinding
import com.example.projecteyebrow.viewModel.FindPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class FindPasswordFragment : Fragment() {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentFindPasswordBinding? = null
    private val binding get() = _binding!!
    private val findPasswordViewModel: FindPasswordViewModel by viewModels()

    private val userEmail: String by lazy { setUserEmail() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isResetEmailSuccess()
        binding.FindPasswordBtn.setOnClickListener { sendResetEmail(userEmail) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserEmail(): String = binding.EmailInput.text.toString()

    private fun clearEmailInputField(): Unit? = binding.EmailInput.text?.clear()

    private fun toLogInFragment() = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, LogInFragment())
        .commit()

    private fun sendResetEmail(userEmail: String): Job =
        findPasswordViewModel.sendResetPassword(userEmail)

    private fun isResetEmailSuccess(): Unit =
        findPasswordViewModel.isResetEmailSend.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                toastMessage.apply { setText("이메일 발송이 완료되었습니다.") }.show()
                toLogInFragment()
            } else {
                toastMessage.apply { setText("이메일을 다시 확인해주세요.") }.show()
                clearEmailInputField()
            }
        }
}