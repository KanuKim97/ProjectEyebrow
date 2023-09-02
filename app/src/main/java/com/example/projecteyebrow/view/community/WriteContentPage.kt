package com.example.projecteyebrow.view.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.projecteyebrow.R
import com.example.projecteyebrow.viewModel.WriteContentViewModel

@Composable
fun WriteCommunityContentPage(
    navController: NavController,
    writeContentViewModel: WriteContentViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        topBar = {
            WriteCommunityContentTopBar(
                loadTempContent = {  },
                saveTempContent = {
                    writeContentViewModel.temporarySaveContent(title, content)
                },
                uploadCommunityContent = {
                    writeContentViewModel.uploadCommunityContent(title, content)
                }
            )
        }
    ) { contentPadding ->
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = stringResource(id = R.string.communityTitle)) },
                singleLine = true,
                shape = ShapeDefaults.Medium
            )
            Text(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                text = stringResource(id = R.string.photoNotice),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light
            )
            UploadPhotoRow(modifier = Modifier)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp),
                value = content,
                onValueChange = { content = it },
                label = { Text(text = stringResource(id = R.string.InputContent_Hint)) },
                shape = ShapeDefaults.Medium
            )
        }
    }
}