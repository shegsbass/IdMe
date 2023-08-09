package com.shegs.idme.events

import com.shegs.idme.model.card.CardEntity
import java.time.LocalDateTime

sealed interface CardEvent{

    object SaveCard: CardEvent

    data class SetCardName(val cardName: String): CardEvent

    data class SetCardCreateDate(val cardCreateDate: LocalDateTime): CardEvent

    data class DeleteCard(val card: CardEntity): CardEvent
}