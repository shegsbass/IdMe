package com.shegs.idme.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.shegs.idme.events.CardEvents
import com.shegs.idme.viewModels.CardViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: CardViewModel, navController: NavController) {
    val cardState = viewModel.cardState.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(CardEvents.ShowDialog)
                }

            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Card"
                )
            }
        },
        content = {_ ->
            if (cardState.isAddingCard) {
                AddCardDialog(
                    viewModel = viewModel,
                    navController = navController,
                    onCardCreated = { cardName ->
                        // Navigate to the input info screen and pass the card name
                        navController.navigate("input/$cardName")
                    })
            }
            val cards = viewModel.getAllCards.collectAsState(initial = emptyList()).value

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cards) { card ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                    viewModel.onCardClicked(card) // Notify ViewModel about card click
                                    navController.navigate("info/${card.infoId}") // Navigate to InfoDetailsScreen
                            },
                        elevation = CardDefaults.cardElevation(4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "${card.cardName}",
                                fontSize = 20.sp
                            )
                            Text(
                                text = "${card.createdAt}",
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = { viewModel.viewModelScope.launch {
                                        viewModel.onEvent(CardEvents.DeleteCard(card)) }
                                    }

                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete Card"
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    )
}