package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.FragmentTemporaryContentBinding
import com.example.projecteyebrow.view.adapter.TempContentAdapter
import com.example.projecteyebrow.viewModel.TemporaryContentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class TemporaryContentFragment : Fragment() {
    private var _binding: FragmentTemporaryContentBinding? = null
    private val binding: FragmentTemporaryContentBinding get() = _binding!!
    private val tempContentViewModel: TemporaryContentViewModel by viewModels()

    private val tempContentList: RecyclerView by lazy { binding.TemporaryContentList }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAllTempContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTemporaryContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTempContentList()
        setTempContentList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadAllTempContent(): Job = tempContentViewModel.loadAllTempContent()

    private fun initTempContentList() {
        tempContentList.layoutManager = LinearLayoutManager(requireContext())
        tempContentList.setHasFixedSize(true)
    }

    private fun setTempContentList() {
        tempContentViewModel.tempContent.observe(viewLifecycleOwner) {
            tempContentList.adapter = TempContentAdapter(it)
        }
    }
}