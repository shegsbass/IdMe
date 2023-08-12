package com.shegs.idme.ui

import android.util.Log
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.shegs.idme.events.CardEvents
import com.shegs.idme.viewModels.CardViewModel
import kotlinx.coroutines.launch

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
        onDismissRequest = { viewModel.viewModelScope.launch {
            try {
                viewModel.onEvent(CardEvents.HideDialog)
            } catch (e: Exception) {
                Log.e("AddCardDialog", "Error hiding dialog", e)
            }
        }
        },
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
                        viewModel.viewModelScope.launch {
                            try {
                                viewModel.onEvent(CardEvents.SetCardName(it))
                            } catch (e: Exception) {
                                Log.e("AddCardDialog", "Error setting card name", e)
                            }
                        }
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
                        viewModel.viewModelScope.launch {
                            try {
                                viewModel.onEvent(CardEvents.SaveCard)
                                onCardCreated(cardState.cardName)
                                navController.navigate("input/${cardState.cardName}") // Use the correct route name
                            } catch (e: Exception) {
                                Log.e("AddCardDialog", "Error saving card", e)
                            }
                        }
                    }
                ) {
                    Text(text = "Create Card")
                }

            }
        })

}