package com.example.projecteyebrow

import androidx.compose.runtime.Composable
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
import com.example.projecteyebrow.view.signIn.SignInPage
import com.example.projecteyebrow.view.tempContent.TempContentListItem
import com.example.projecteyebrow.view.tempContent.TempContentListSection

@Composable
fun EyebrowNavHost(navHostController: NavHostController) {
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
            DetailedItemPage()
        }
        composable(route = TattooistDetailed.route) {
            DetailedTattooistPage()
        }
        composable(route = LogIn.route) {
            LogInPage(navController = navHostController)
        }
        composable(route = SignIn.route) {
            SignInPage(navController = navHostController)
        }
        composable(route = FindPWD.route) {
            FindPWDPage(navController = navHostController)
        }
    }
}