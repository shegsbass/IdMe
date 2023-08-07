package com.shegs.idme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun InputInfo(onGenerateQRCode: (String) -> Unit) {
    val context = LocalContext.current

    // Remember the values of the input fields
    var fullNameState by remember { mutableStateOf(TextFieldValue()) }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var phoneNumberState by remember { mutableStateOf(TextFieldValue()) }

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

        Button(
            onClick = {
                val qrText = buildString {
                    append("Full Name: ${fullNameState.text}\n")
                    append("Email: ${emailState.text}\n")
                    append("Phone Number: ${phoneNumberState.text}")
                }
                onGenerateQRCode(qrText)
            }
        ) {
            Text("Generate QR Code")
        }
    }
}