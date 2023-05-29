package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentCommunityBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.view.adapter.CommunityAdapter
import com.example.projecteyebrow.viewModel.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CommunityFragment : Fragment() {
    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentCommunityBinding? = null
    private val binding: FragmentCommunityBinding get() = _binding!!
    private val communityViewModel: CommunityViewModel by viewModels()

    private val communityList: RecyclerView by lazy { binding.communityView }

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
        initCommunityList()
        setCommunityList()

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

    private fun initCommunityList(): RecyclerView = communityList.apply {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

    private fun setCommunityList(): Job = lifecycleScope.launch(mainDispatcher) {
        communityViewModel.communityList.collect{
            communityList.adapter = CommunityAdapter(it)
        }
    }

}