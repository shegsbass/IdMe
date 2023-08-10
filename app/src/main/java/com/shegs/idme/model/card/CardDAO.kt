package com.shegs.idme.model.card

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDAO {
    @Insert
    suspend fun insertCard(card: CardEntity)

    @Query("SELECT * FROM card_table ORDER BY createdAt DESC")
    suspend fun getAllCards(): List<CardEntity>

    @Delete
    suspend fun deleteCard(card: CardEntity)

}

