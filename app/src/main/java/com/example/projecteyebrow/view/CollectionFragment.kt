package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.FragmentCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding: FragmentCollectionBinding get() = _binding!!

    private val collectionList: RecyclerView by lazy { binding.collectionList }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initCollectionList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCollectionList(): RecyclerView = collectionList.apply {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

}