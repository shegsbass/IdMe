package com.shegs.idme.states

import com.shegs.idme.model.card.CardEntity
import java.time.LocalDateTime

data class CardState(
    val cards: List<CardEntity> = emptyList(),

    val isAddingCard: Boolean = false,

    val cardName: String = "",

    val cardCreateDate: LocalDateTime
)
