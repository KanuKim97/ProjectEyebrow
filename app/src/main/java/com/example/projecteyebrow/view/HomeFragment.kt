package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.projecteyebrow.databinding.FragmentHomeBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.view.adapter.adapterItems.BrandNewItem
import com.example.projecteyebrow.view.adapter.adapterItems.HotViewItem
import com.example.projecteyebrow.view.adapter.adapterItems.TattooistItem
import com.example.projecteyebrow.view.home.BrandNewSection
import com.example.projecteyebrow.view.home.HotViewSection
import com.example.projecteyebrow.view.home.TattooistSection
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

    // Recycler View Test
    private val exampleBrandNew = ArrayList<BrandNewItem>()
    private val exampleHotView = ArrayList<HotViewItem>()
    private val exampleTattooistView = ArrayList<TattooistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (element in 1..5) {
            exampleBrandNew.add(BrandNewItem("제목 $element", "내용 $element"))
            exampleHotView.add(HotViewItem("제목 $element", "내용 $element"))
            exampleTattooistView.add(TattooistItem("제목 $element", "내용 $element"))
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
        binding.ListSection.setContent {
            MaterialTheme {
                Column {
                    BrandNewSection(brandNewItemList = exampleBrandNew)
                    HotViewSection(hotViewItemList = exampleHotView)
                    TattooistSection(tattooistItemList = exampleTattooistView)
                }
            } 
        }
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

}