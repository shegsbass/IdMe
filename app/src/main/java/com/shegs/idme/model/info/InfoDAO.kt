package com.shegs.idme.model.info

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface InfoDAO {

    @Upsert
    suspend fun insertInfo(info: InfoEntity)

    @Query("SELECT * FROM information_table WHERE info_id = :infoId ORDER BY timestamp DESC")
    suspend fun getInfoByInfoId(infoId: Int): List<InfoEntity>
}