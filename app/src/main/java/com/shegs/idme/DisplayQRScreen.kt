package com.shegs.idme

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun DisplayQRScreen(qrCodeBitmap: ImageBitmap) {

        Image(
        bitmap = qrCodeBitmap,
        contentDescription = "QR Code",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentScale = ContentScale.Fit
    )
}