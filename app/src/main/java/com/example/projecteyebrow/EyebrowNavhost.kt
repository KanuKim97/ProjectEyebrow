package com.example.projecteyebrow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecteyebrow.view.collection.CollectionPage
import com.example.projecteyebrow.view.community.CommunityContentPage
import com.example.projecteyebrow.view.community.WriteCommunityContentPage
import com.example.projecteyebrow.view.detailed.DetailedItemPage
import com.example.projecteyebrow.view.detailed.DetailedTattooistPage
import com.example.projecteyebrow.view.findPWD.FindPWDPage
import com.example.projecteyebrow.view.home.HomePage
import com.example.projecteyebrow.view.logIn.LogInPage
import com.example.projecteyebrow.view.profile.ProfilePage
import com.example.projecteyebrow.view.signIn.SignInPage
import com.example.projecteyebrow.view.tempContent.TempContentListSection
import com.example.projecteyebrow.viewModel.MainViewModel

@Composable
fun EyebrowNavHost(
    navHostController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val authState by mainViewModel.isAuthAlive.collectAsState(initial = false)

    NavHost(
        navController = navHostController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {
            HomePage(
                navController = navHostController,
                brandNewList = arrayListOf(),
                hotViewList = arrayListOf(),
                tattooistList = arrayListOf()
            )
        }
        composable(route = Collection.route) {
            CollectionPage(navController = navHostController)
        }
        composable(route = Community.route) {
            CommunityContentPage (navController = navHostController)
        }
        composable(route = WriteContent.route) {
            WriteCommunityContentPage(navController = navHostController)
        }
        composable(route = TempContent.route) {
            TempContentListSection()
        }
        composable(route = DetailedItem.route) {
            DetailedItemPage(navController = navHostController)
        }
        composable(route = TattooistDetailed.route) {
            DetailedTattooistPage()
        }
        composable(route = SignIn.route) {
            SignInPage(navController = navHostController)
        }
        composable(route = FindPWD.route) {
            FindPWDPage(navController = navHostController)
        }
        composable(route = Profile.route) {
            if (authState) {
                ProfilePage(navController = navHostController)
            } else {
                LogInPage(navController = navHostController)
            }
        }
    }
}