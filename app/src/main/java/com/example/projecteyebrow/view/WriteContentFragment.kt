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
import com.example.projecteyebrow.databinding.FragmentWriteContentBinding
import com.example.projecteyebrow.view.community.WriteCommunityContentSection
import com.example.projecteyebrow.viewModel.WriteContentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WriteContentFragment : Fragment() {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentWriteContentBinding? = null
    private val binding: FragmentWriteContentBinding get() = _binding!!
    private val writeCommunityViewModel: WriteContentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.writeContentSection.setContent {
            MaterialTheme {
                WriteCommunityContentSection(loadTempContent = { toTemporaryContentFragment() })
            }
        }

        writeCommunityViewModel.isSaveSuccess.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                toastMessage.apply { setText(getString(R.string.SaveSuccess_MSG)) }.show()
                toCommunityFragment()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toCommunityFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, CommunityFragment())
        .commit()

    private fun toTemporaryContentFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, TemporaryContentFragment())
        .commit()

}