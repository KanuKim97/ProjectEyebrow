package com.example.projecteyebrow.view.community

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projecteyebrow.R
import com.example.projecteyebrow.TempContent
import com.example.projecteyebrow.view.util.States
import com.example.projecteyebrow.viewModel.WriteContentViewModel

@Composable
fun WriteCommunityContentPage(
    navController: NavController,
    writeContentViewModel: WriteContentViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    val isSaveState by writeContentViewModel.isSaveSuccess.collectAsState()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUriList by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiPhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { imageUriList = it }
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        topBar = {
            WriteCommunityContentTopBar(
                loadTempContent = {
                    navController.navigate(TempContent.route)
                },
                saveTempContent = {
                    writeContentViewModel.temporarySaveContent(title, content, imageUriList)

                    when (isSaveState) {
                        is States.IsSuccess -> Toast.makeText(
                            localContext,
                            "저장 성공",
                            Toast.LENGTH_SHORT
                        ).show()
                        is States.IsFailed -> Toast.makeText(
                            localContext,
                            "저장 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                        else -> { }
                    }
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
            UploadPhotoRow(
                modifier = Modifier,
                imageUriList = imageUriList,
                onClickOpenPhotoPicker = {
                    multiPhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
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