package com.example.projecteyebrow.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentSigninBinding
import com.example.projecteyebrow.view.signIn.SignInPage
import com.example.projecteyebrow.viewModel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null
    private val binding: FragmentSigninBinding get() = _binding!!
    private val createAccountViewModel: SignInViewModel by viewModels()

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
        binding.SignInPage.setContent {
            MaterialTheme {
                SignInPage(toLogInPageClicked = { toLoginFragment() })
            }
        }

        createAccountViewModel.isCreateSuccess.observe(viewLifecycleOwner) {
            if (it.isSuccess) { toProfileFragment() }
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

    private fun toLoginFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, LogInFragment())
        .commit()
}