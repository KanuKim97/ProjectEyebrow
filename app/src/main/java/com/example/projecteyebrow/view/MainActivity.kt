package com.example.projecteyebrow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.ActivityMainBinding
import com.example.projecteyebrow.di.dispatcherQualifier.MainDispatcher
import com.example.projecteyebrow.viewModel.MainViewModel
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher

    private val _binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragmentLayout()

        _binding.BottomNavBar.setOnItemSelectedListener(this)
        setContentView(_binding.root)
    }

    private fun initFragmentLayout(): Int = supportFragmentManager.beginTransaction()
            .replace(R.id.FragmentContainer, HomeFragment())
            .commit()

    private fun toHomeFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, HomeFragment())
        .commit()

    private fun toProfileFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, ProfileFragment())
        .commit()

    private fun toCommunityFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, CommunityFragment())
        .commit()

    private fun toCollectionFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, CollectionFragment())
        .commit()

    private fun toLogInFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, LogInFragment())
        .commit()

    private fun checkUserSession(): Job = lifecycleScope.launch(mainDispatcher) {
        mainViewModel.userCurrentSession.collect {
            if (!it) {
                toLogInFragment()
            } else {
                toProfileFragment()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuHome -> toHomeFragment()
            R.id.menuProfile -> checkUserSession()
            R.id.menuCommunity -> toCommunityFragment()
            R.id.menuCollection -> toCollectionFragment()
        }
        return true
    }

}