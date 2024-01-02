package com.example.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState

@Composable
fun EyeBrowImageLoader(
    imageModel: () -> Any?,
    modifier: Modifier = Modifier,
    loading: @Composable (BoxScope.(GlideImageState.Loading) -> Unit)? = null,
    success: @Composable (BoxScope.(GlideImageState.Success, Painter) -> Unit)? = null,
    failure: @Composable (BoxScope.(GlideImageState.Failure) -> Unit)? = null
) {
    GlideImage(
        imageModel = imageModel,
        modifier = modifier,
        imageOptions = ImageOptions(
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        ),
        loading = loading,
        success = success,
        failure = failure
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewImgLoader() {
    EyeBrowImageLoader(
        imageModel = { "" },
        loading = { loading ->
            if (GlideImageState.Loading == loading) {
                CircularProgressIndicator()
            }
        },
        success = { _, painter ->
            Image(
                painter = painter,
                contentDescription = "image"
            )
        },
        failure = { Text(text = "Load Failed", textAlign = TextAlign.Center) }
    )
}