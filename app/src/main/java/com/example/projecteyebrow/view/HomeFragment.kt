package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.projecteyebrow.databinding.FragmentHomeBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.view.adapter.BrandNewAdapter
import com.example.projecteyebrow.view.adapter.HotViewAdapter
import com.example.projecteyebrow.view.adapter.TattooistAdapter
import com.example.projecteyebrow.view.adapter.adapterItems.BrandNewItem
import com.example.projecteyebrow.view.adapter.adapterItems.HotViewItem
import com.example.projecteyebrow.view.adapter.adapterItems.TattooistItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @MainDispatcher @Inject lateinit var mainDispatcher: CoroutineDispatcher

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val mainBanner: ViewPager2 by lazy { binding.mainBanner }
    private val brandNewList: RecyclerView by lazy { binding.BrandNewList }
    private val hotViewList: RecyclerView by lazy { binding.HotViewList }
    private val tattooistList: RecyclerView by lazy { binding.TattooistList }

    // Recycler View Test
    private val exampleBrandNew = ArrayList<BrandNewItem>()
    private val exampleHotView = ArrayList<HotViewItem>()
    private val exampleTattooistView = ArrayList<TattooistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (element in 1..5) {
            exampleBrandNew.add(BrandNewItem("Task $element", "$element"))
            exampleHotView.add(HotViewItem("Task $element", "$element"))
            exampleTattooistView.add(TattooistItem("Task $element", "$element"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initMainBanner()
        initBrandNewList()
        initHotViewList()
        initTattooistList()

        setBrandNewAdapter()
        setHotViewAdapter()
        setTattooistAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMainBanner(): Job = lifecycleScope.launch(mainDispatcher) {
        mainBanner.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            scrollIndicators = ViewPager2.SCROLL_INDICATOR_END
        }
    }

    private fun initBrandNewList(): Job = lifecycleScope.launch(mainDispatcher) {
        brandNewList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun initHotViewList(): Job = lifecycleScope.launch(mainDispatcher) {
        hotViewList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun initTattooistList(): Job = lifecycleScope.launch(mainDispatcher) {
        tattooistList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setBrandNewAdapter() { brandNewList.adapter = BrandNewAdapter(exampleBrandNew) }

    private fun setHotViewAdapter() { hotViewList.adapter = HotViewAdapter(exampleHotView) }

    private fun setTattooistAdapter() { tattooistList.adapter = TattooistAdapter(exampleTattooistView) }

}