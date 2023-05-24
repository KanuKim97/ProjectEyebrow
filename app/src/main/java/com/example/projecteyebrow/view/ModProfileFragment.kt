package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentModProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModProfileFragment : Fragment() {
    private var _binding: FragmentModProfileBinding? = null
    private val binding: FragmentModProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}