package com.shegs.idme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shegs.idme.model.info.InfoEntity

@Composable
fun DisplayInfoScreen(info: InfoEntity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Personal Information",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Text("First Name: ${info.firstName}")
        Text("Last Name: ${info.lastName}")
        Text("Email Address: ${info.emailAddress}")

        // Display other fields like home address, phone number, etc.

        Spacer(modifier = Modifier.height(16.dp))

        
    }
}
