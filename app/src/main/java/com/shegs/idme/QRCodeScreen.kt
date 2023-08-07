package com.shegs.idme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
@Composable
fun QRCodeScreen() {
    val context = LocalContext.current

    // Remember the values of the input fields
    var fullNameState by remember { mutableStateOf(TextFieldValue()) }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var phoneNumberState by remember { mutableStateOf(TextFieldValue()) }

    var qrCodeBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(fullNameState, emailState, phoneNumberState) {
        val qrText = buildString {
            append("Full Name: ${fullNameState.text}\n")
            append("Email: ${emailState.text}\n")
            append("Phone Number: ${phoneNumberState.text}")
        }

        val bitmap = withContext(Dispatchers.Default) {
            generateQRCode(qrText, 300) // Adjust the size as needed
        }
        qrCodeBitmap = bitmap.asImageBitmap()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Full Name
        TextField(
            value = fullNameState.text,
            onValueChange = { fullNameState = fullNameState.copy(text = it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Full Name") }
        )

        // Email
        TextField(
            value = emailState.text,
            onValueChange = { emailState = emailState.copy(text = it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Email") }
        )

        // Phone Number
        TextField(
            value = phoneNumberState.text,
            onValueChange = { phoneNumberState = phoneNumberState.copy(text = it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Phone Number") }
        )

        if (qrCodeBitmap != null) {
            Image(
                bitmap = qrCodeBitmap!!,
                contentDescription = "QR Code",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
        } else {
            // Handle error or loading state
        }
    }
}