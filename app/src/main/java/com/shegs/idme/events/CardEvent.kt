package com.shegs.idme.events

import com.shegs.idme.model.card.CardEntity
import java.util.Date

sealed interface CardEvent{

    object SaveCard: CardEvent

    object ShowDialog: CardEvent

    object HideDialog: CardEvent

    data class SetCardName(val cardName: String): CardEvent

    data class SetTimeStamp(val createdAt: Date): CardEvent

    data class DeleteCard(val card: CardEntity): CardEvent
}