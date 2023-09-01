package com.example.projecteyebrow.view.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecteyebrow.view.ProfileFragment
import com.example.projecteyebrow.view.community.CommunityContentPage
import com.example.projecteyebrow.view.community.WriteCommunityContentPage
import com.example.projecteyebrow.view.home.HomePage
import com.example.projecteyebrow.view.logIn.LogInPage

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
            CommunityContentPage (onClickBtn = { /*TODO*/ })
        }
        composable(route = WriteContentPage.route) {
            WriteCommunityContentPage(loadTempContent = { /*TODO*/ })
        }

        composable(route = LogInPage.route) {
            LogInPage(
                toFindPWDBtnClick = { /*TODO*/ },
                toSignInAccountBtnClick = { /*TODO*/ }
            )
        }
    }
}