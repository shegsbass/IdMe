package com.shegs.idme.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegs.idme.events.CardEvent
import com.shegs.idme.model.card.CardDAO
import com.shegs.idme.model.card.CardEntity
import com.shegs.idme.states.CardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class CardViewModel(
    private val cardDAO: CardDAO
): ViewModel() {
    private val _state = MutableStateFlow(CardState())

    private val cardState = _state

    fun onEvent(event: CardEvent){
        when(event){
            is CardEvent.DeleteCard -> {
                viewModelScope.launch {
                    cardDAO.deleteCard(event.card)
                }
            }

            CardEvent.SaveCard -> {
                val cardName = cardState.value.cardName

                if (cardName.isBlank()){
                    return
                }
                val card = CardEntity(
                    cardName = cardName,
                    timestamp = LocalDateTime.now()
                )
                viewModelScope.launch {
                    cardDAO.insertCard(card)
                }
            }

            is CardEvent.SetCardName -> {
                cardState.update { it.copy(
                    cardName = event.cardName
                )
                }
            }

            is CardEvent.SetTimeStamp -> {
                cardState.update { it.copy(
                    timeStamp = event.timeStamp
                ) }
            }
        }
    }
}