package com.shegs.idme.events

import com.shegs.idme.model.card.CardEntity
import java.util.Date

sealed interface CardEvents{

    object SaveCard: CardEvents

    object ShowDialog: CardEvents

    object HideDialog: CardEvents

    data class SetCardName(val cardName: String): CardEvents

    data class SetTimeStamp(val createdAt: Date): CardEvents

    data class DeleteCard(val card: CardEntity): CardEvents

}