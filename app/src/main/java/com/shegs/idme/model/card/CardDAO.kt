package com.shegs.idme.model.card

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shegs.idme.model.info.InfoEntity

@Dao
interface CardDAO {
    @Insert
    suspend fun insertCard(card: CardEntity)

    @Query("SELECT * FROM card_table ORDER BY createdAt DESC")
    suspend fun getAllCards(): List<CardEntity>

    @Query("SELECT * FROM information_table WHERE info_id = :infoId ORDER BY timestamp DESC")
    suspend fun getInformationByInformationId(infoId: Int): List<InfoEntity>

    @Delete
    suspend fun deleteCard(card: CardEntity)

}

