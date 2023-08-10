package com.shegs.idme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun InputInfoScreen(cardName: String, onGenerateQRCode: (String) -> Unit) {


    // Remember the values of the input fields
    var firstName by remember { mutableStateOf(TextFieldValue()) }
    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var emailAddress by remember { mutableStateOf(TextFieldValue()) }
    var homeAddress by remember { mutableStateOf(TextFieldValue()) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
    var instagramHandle by remember { mutableStateOf(TextFieldValue()) }
    var twitterHandle by remember { mutableStateOf(TextFieldValue()) }
    var bio by remember { mutableStateOf(TextFieldValue()) }
    var hobbies by remember { mutableStateOf(TextFieldValue()) }
    var bankAccountName by remember { mutableStateOf(TextFieldValue()) }
    var bankAccountNumber by remember { mutableStateOf(TextFieldValue()) }
    var bankName by remember { mutableStateOf(TextFieldValue()) }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Create QR for $cardName", // Display the card name as the title
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )

            TextField(
                value = firstName.text,
                onValueChange = { firstName = firstName.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("First Name") }
            )

            TextField(
                value = lastName.text,
                onValueChange = { lastName = lastName.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Last Name") }
            )

            TextField(
                value = emailAddress.text,
                onValueChange = { emailAddress = emailAddress.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Email") }
            )

            TextField(
                value = homeAddress.text,
                onValueChange = { homeAddress = homeAddress.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Home Address") }
            )


            TextField(
                value = phoneNumber.text,
                onValueChange = { phoneNumber = phoneNumber.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Phone Number") }
            )

            TextField(
                value = instagramHandle.text,
                onValueChange = { instagramHandle = instagramHandle.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Instagram Handle") }
            )

            TextField(
                value = twitterHandle.text,
                onValueChange = { twitterHandle = twitterHandle.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Twitter Handle") }
            )

            TextField(
                value = bio.text,
                onValueChange = { bio = bio.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Bio") }
            )

            TextField(
                value = hobbies.text,
                onValueChange = { hobbies = hobbies.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Hobbies/Interest") }
            )

            TextField(
                value = bankAccountName.text,
                onValueChange = { bankAccountName = bankAccountName.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Bank Account Name") }
            )

            TextField(
                value = bankAccountNumber.text,
                onValueChange = { bankAccountNumber = bankAccountNumber.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Bank Account Number") }
            )

            TextField(
                value = bankName.text,
                onValueChange = { bankName = bankName.copy(text = it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Bank Name") }
            )

            Button(
                onClick = {
                    val qrText = buildString {
                        append("First Name: ${firstName.text}\n")
                        append("Last Name: ${lastName.text}\n")
                        append("Email Address: ${emailAddress.text}")
                        append("Home Address: ${homeAddress.text}")
                        append("Phone Number: ${phoneNumber.text}")
                        append("Instagram Handle: ${instagramHandle.text}")
                        append("Twitter Handle: ${twitterHandle.text}")
                        append("Bio: ${bio.text}")
                        append("Hobbies: ${hobbies.text}")
                        append("Bank Account Name: ${bankAccountName.text}")
                        append("Bank Account Number: ${bankAccountNumber.text}")
                        append("Bank Name: ${bankName.text}")
                    }
                    onGenerateQRCode(qrText)
                }
            ) {
                Text("Generate QR Code")
            }
        }

    }
}