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
import com.example.projecteyebrow.databinding.FragmentCommunityBinding
import com.example.projecteyebrow.Qualifier.MainDispatcher
import com.example.projecteyebrow.view.community.CommunityContentList
import com.example.projecteyebrow.viewModel.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CommunityFragment : Fragment() {
    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentCommunityBinding? = null
    private val binding: FragmentCommunityBinding get() = _binding!!
    private val communityViewModel: CommunityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.CommunityList.setContent { MaterialTheme { CommunityContentList() } }
        binding.writeBtn.setOnClickListener {
            lifecycleScope.launch(mainDispatcher) {
                communityViewModel.userCurrentSession.collect {
                    if (it) {
                        toWriteCommunityFragment()
                    } else {
                        toastMessage.apply { setText("로그인을 먼저 수행해 주세요") }.show()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toWriteCommunityFragment() = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, WriteContentFragment())
        .addToBackStack(null)
        .commit()
}