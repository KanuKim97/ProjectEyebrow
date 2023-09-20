package com.example.projecteyebrow.view.community

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecteyebrow.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteCommunityContentTopBar(
    loadTempContent: () -> Unit,
    saveTempContent: () -> Unit,
    uploadCommunityContent: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = loadTempContent,
                    content = {
                        Text(
                            text = stringResource(id = R.string.TemporaryLoad_Btn),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    shape = ShapeDefaults.Small
                )
                Spacer(modifier = Modifier.size(3.dp))
                Button(
                    onClick = saveTempContent,
                    content = {
                        Text(
                            text = stringResource(id = R.string.TemporarySave_Btn),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    shape = ShapeDefaults.Small
                )
                Spacer(modifier = Modifier.size(3.dp))
                Button(
                    onClick = uploadCommunityContent,
                    content = {
                        Text(
                            text = stringResource(id = R.string.Upload_Btn),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    shape = ShapeDefaults.Small
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}