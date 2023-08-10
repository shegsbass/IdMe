package com.shegs.idme.repositories

import com.shegs.idme.model.card.CardDAO
import com.shegs.idme.model.card.CardEntity
import javax.inject.Inject

class CardRepository @Inject constructor(private val cardDAO: CardDAO ){
    suspend fun insertCard(card: CardEntity){
        cardDAO.insertCard(card)
    }

    suspend fun getAllCards(): List<CardEntity> {
        return cardDAO.getAllCards()
    }

    suspend fun deleteCard(card: CardEntity){
        cardDAO.deleteCard(card)
    }

}