package com.shegs.idme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shegs.idme.viewModels.InfoViewModel

@Composable
fun InfoDetailsScreen(infoId: Int, viewModel: InfoViewModel) {
    val infoEntity by viewModel.getInfoById.collectAsState()

    // Fetch info details if not already fetched
    if (infoEntity == null) {
        // Fetch information based on the provided infoId
        viewModel.fetchInfoById(infoId)
    }

    // Display info details
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display info details
        infoEntity?.let { infoEntity ->
            // Your UI code to display infoEntity attributes goes here
            Text(
                text = "First Name: ${infoEntity.firstName}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Last Name: ${infoEntity.lastName}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )


        }
    }
}


//else {
//    // If infoState is not fetched yet, show loading or placeholder
//    Text(
//        text = "Loading info...",
//        fontSize = 18.sp,
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier.padding(vertical = 16.dp)
//    )
//}
