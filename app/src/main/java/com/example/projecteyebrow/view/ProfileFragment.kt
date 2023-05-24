package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.projecteyebrow.databinding.FragmentProfileBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.viewModel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    @Inject lateinit var toastMessage: Toast
    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch(mainDispatcher) {
            profileViewModel.userProfile.collect { userProfile ->
                binding.nicknameTxt.text = userProfile.userName
                binding.emailTxt.text = userProfile.userEmail
            }
        }

        binding.modProfileBtn.setOnClickListener {  }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}