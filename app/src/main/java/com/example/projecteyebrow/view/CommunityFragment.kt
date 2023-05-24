package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentCommunityBinding
import com.example.projecteyebrow.viewModel.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class CommunityFragment : Fragment() {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentCommunityBinding? = null
    private val binding: FragmentCommunityBinding get() = _binding!!
    private val communityViewModel: CommunityViewModel by viewModels()

    private val communityList: RecyclerView by lazy { binding.communityView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkCurrentSession()
    }

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

        binding.writeBtn.setOnClickListener {
            communityViewModel.userCurrentSession.observe(viewLifecycleOwner) { currentSession ->
                if (currentSession != null) {
                    toWriteCommunityFragment()
                } else {
                    toastMessage.apply { setText("로그인을 먼저 수행해주세요!") }.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkCurrentSession(): Job = communityViewModel.checkUserSession()

    private fun toWriteCommunityFragment() = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, WriteContentFragment())
        .addToBackStack(null)
        .commit()

    private fun initCommunityList(): RecyclerView = communityList.apply {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

}