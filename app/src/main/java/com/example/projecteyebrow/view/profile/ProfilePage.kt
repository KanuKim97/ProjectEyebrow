package com.example.projecteyebrow.view.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projecteyebrow.Profile
import com.example.projecteyebrow.view.util.States
import com.example.projecteyebrow.viewModel.ProfileViewModel

@Composable
fun ProfilePage(
    navController: NavController,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val userProfile by profileViewModel.userProfile.collectAsState()
    val userSession by profileViewModel.isLogOutState.collectAsState()
    val localContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        LogOutDialog(
            onDismissRequestOnDialog = { openDialog.value = false },
            onConfirmClick = {
                profileViewModel.userAccountLogOut()
                when(userSession) {
                    is States.Idle -> {  }
                    is States.IsLoading -> {  }
                    is States.IsSuccess -> navController.navigate(Profile.route)
                    is States.IsFailed -> {
                        Toast.makeText(
                            localContext,
                            "",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            onDismissClick = { openDialog.value = false }
        )
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        floatingActionButton = {
            LogOutFloatingBtn(onDialogClick = { openDialog.value = true })
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        Column(
            modifier = modifier.padding(contentPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Spacer(modifier = modifier.size(50.dp))
                ProfileInfoRow(userName = userProfile.userName)
            }
        )
    }
}