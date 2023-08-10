package com.shegs.idme.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegs.idme.events.CardEvents
import com.shegs.idme.model.card.CardEntity
import com.shegs.idme.repositories.CardRepository
import com.shegs.idme.states.CardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {
    val currentDate = Date()
    private val _state = MutableStateFlow(CardState(createdAt = currentDate))
    val cardState = _state

    private val _getAllCards = MutableStateFlow<List<CardEntity>>(emptyList())
    val getAllCards: StateFlow<List<CardEntity>> = _getAllCards

    init {
        fetchAllCards()
    }

    private fun fetchAllCards() {
        viewModelScope.launch {
            val cards = cardRepository.getAllCards()
            _getAllCards.emit(cards)
        }
    }


    fun onEvent(event: CardEvents) {
        when (event) {
            is CardEvents.DeleteCard -> {
                viewModelScope.launch {
                    cardRepository.deleteCard(event.card)
                    fetchAllCards()
                }
            }

            CardEvents.SaveCard -> {
                val cardName = cardState.value.cardName

                if (cardName.isBlank()) {
                    return
                }
                val card = CardEntity(
                    cardName = cardName,
                    createdAt = LocalDateTime.now()
                )
                viewModelScope.launch {
                    cardRepository.insertCard(card)
                    fetchAllCards()
                    cardState.update { it.copy(isAddingCard = false) }
                }
            }

            is CardEvents.SetCardName -> {
                cardState.update { it.copy(cardName = event.cardName) }
            }

            is CardEvents.SetTimeStamp -> {
                cardState.update { it.copy(createdAt = event.createdAt) }
            }

            CardEvents.ShowDialog -> {
                cardState.update { it.copy(isAddingCard = true) }
            }

            CardEvents.HideDialog -> {
                cardState.update { it.copy(isAddingCard = false) }
            }

            else -> {}
        }
    }
}