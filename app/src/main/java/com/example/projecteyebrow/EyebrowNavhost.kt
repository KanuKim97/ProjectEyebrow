package com.example.projecteyebrow

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecteyebrow.view.community.CommunityContentPage
import com.example.projecteyebrow.view.community.WriteCommunityContentPage
import com.example.projecteyebrow.view.findPWD.FindPWDPage
import com.example.projecteyebrow.view.home.HomePage
import com.example.projecteyebrow.view.logIn.LogInPage
import com.example.projecteyebrow.view.signIn.SignInPage

@Composable
fun EyebrowNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = HomePage.route
    ) {
        composable(route = HomePage.route) {
            HomePage(
                brandNewList = arrayListOf(),
                hotViewList = arrayListOf(),
                tattooistList = arrayListOf()
            )
        }
        composable(route = CommunityPage.route) {
            CommunityContentPage (navController = navHostController)
        }
        composable(route = WriteContentPage.route) {
            WriteCommunityContentPage(navController = navHostController)
        }
        composable(route = LogInPage.route) {
            LogInPage(navController = navHostController)
        }
        composable(route = SignInPage.route) {
            SignInPage(navController = navHostController)
        }
        composable(route = FindPWDPage.route) {
            FindPWDPage(navController = navHostController)
        }
    }
}