package com.example.projecteyebrow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private val _binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val homeFragment: HomeFragment = HomeFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()
    private val communityFragment: CommunityFragment = CommunityFragment()
    private val collectionFragment: CollectionFragment = CollectionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragmentLayout()
        _binding.BottomNavBar.setOnItemSelectedListener(this)

        setContentView(_binding.root)
    }

    private fun initFragmentLayout(): Int =
        supportFragmentManager.beginTransaction()
            .replace(R.id.FragmentContainer, homeFragment)
            .commit()

    private fun toHomeFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, homeFragment)
        .addToBackStack("HomeFragment")
        .commit()

    private fun toProfileFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, profileFragment)
        .addToBackStack("ProfileFragment")
        .commit()

    private fun toCommunityFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, communityFragment)
        .addToBackStack("CommunityFragment")
        .commit()

    private fun toCollectionFragment(): Int = supportFragmentManager.beginTransaction()
        .replace(R.id.FragmentContainer, collectionFragment)
        .addToBackStack("CollectionFragment")
        .commit()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuHome -> toHomeFragment()
            R.id.menuProfile -> toProfileFragment()
            R.id.menuCommunity -> toCommunityFragment()
            R.id.menuCollection -> toCollectionFragment()
        }
        return true
    }

}