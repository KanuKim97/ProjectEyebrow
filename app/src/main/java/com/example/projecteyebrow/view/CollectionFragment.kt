package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.FragmentCollectionBinding
import com.example.projecteyebrow.viewModel.CollectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding: FragmentCollectionBinding get() = _binding!!
    private val collectionViewModel: CollectionViewModel by viewModels()

    private val collectionList: RecyclerView by lazy { binding.collectionList }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCollectionList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /***
     * How can I initialize RecyclerView
     * there's two way init RecyclerView
     * 1. init each parameter of recyclerView
     *  collectionList.layoutManager = LinearLayoutManager(requireContext())
     *  collectionList.setHasFixedSize(true)
     * 2. use Scope function
     *  collectionList.apply {
     *  layoutManager = LinearLayoutManager(requireContext())
     *  setHasFixedSize(true)
     *  }
     */
    private fun initCollectionList(): RecyclerView = collectionList.apply {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

}