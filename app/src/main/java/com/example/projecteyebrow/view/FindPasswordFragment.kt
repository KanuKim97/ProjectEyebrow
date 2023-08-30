package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentFindPasswordBinding
import com.example.projecteyebrow.view.findPassword.FindPWDPage
import com.example.projecteyebrow.viewModel.FindPWDViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FindPasswordFragment : Fragment() {
    @Inject lateinit var toastMessage: Toast
    private var _binding: FragmentFindPasswordBinding? = null
    private val binding: FragmentFindPasswordBinding get() = _binding!!
    private val findPasswordViewModel: FindPWDViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.FindPWDPage.setContent { MaterialTheme { FindPWDPage() } }

        findPasswordViewModel.isResetEmailSend.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                toastMessage.apply { setText("이메일 발송이 완료되었습니다.") }.show()
                toLogInFragment()
            } else {
                toastMessage.apply { setText("이메일을 다시 확인해주세요.") }.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toLogInFragment() = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, LogInFragment())
        .commit()
}