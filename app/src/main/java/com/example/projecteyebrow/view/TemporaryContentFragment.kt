package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projecteyebrow.databinding.FragmentTemporaryContentBinding
import com.example.projecteyebrow.view.tempContent.TempContentListSection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemporaryContentFragment : Fragment() {
    private var _binding: FragmentTemporaryContentBinding? = null
    private val binding: FragmentTemporaryContentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTemporaryContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.TemporaryContentList.setContent { TempContentListSection() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}