package com.shegs.idme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shegs.idme.events.CardEvents
import com.shegs.idme.viewModels.CardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardDialog(
    viewModel: CardViewModel,
    navController: NavController,
    onCardCreated: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardState = viewModel.cardState.collectAsState().value

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { viewModel.onEvent(CardEvents.HideDialog) },
        title = {
                Text(text = "Create Your Card")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = cardState.cardName,
                    onValueChange = {
                        viewModel.onEvent(CardEvents.SetCardName(it))
                    },
                    placeholder = {
                        Text(text = "Personal Info")
                    }
                )
            }
        },
        confirmButton = {
            Box (
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(CardEvents.SaveCard)
                        onCardCreated(cardState.cardName)
                        navController.navigate("input/${cardState.cardName}") // Use the correct route name
                    }
                ) {
                    Text(text = "Create Card")
                }

            }
        })

}