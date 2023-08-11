package com.shegs.idme.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shegs.idme.model.card.CardDAO
import com.shegs.idme.model.card.CardEntity
import com.shegs.idme.model.info.InfoDAO
import com.shegs.idme.model.info.InfoEntity
import com.shegs.idme.utils.DateConverter
import com.shegs.idme.utils.LocalDateTimeConverter

@Database(entities = [CardEntity::class, InfoEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class, LocalDateTimeConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDAO() : CardDAO
    abstract fun InfoDAO() : InfoDAO

}