package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import com.example.projecteyebrow.databinding.FragmentHomeBinding
import com.example.projecteyebrow.qualifier.MainDispatcher
import com.example.projecteyebrow.view.util.BrandNewItem
import com.example.projecteyebrow.view.util.HotViewItem
import com.example.projecteyebrow.view.util.TattooistItem
import com.example.projecteyebrow.view.home.HomePage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @MainDispatcher
    @Inject lateinit var mainDispatcher: CoroutineDispatcher

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

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
        binding.HomePage.setContent {
            MaterialTheme {
                HomePage(
                    brandNewList = exampleBrandNew,
                    hotViewList = exampleHotView,
                    tattooistList =exampleTattooistView
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}