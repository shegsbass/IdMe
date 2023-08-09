package com.shegs.idme.states

import com.shegs.idme.model.card.CardEntity
import java.util.Date

data class CardState(
    var cards: List<CardEntity> = emptyList(),

    var isAddingCard: Boolean = false,

    val cardName: String = "",

    val createdAt: Date
)
