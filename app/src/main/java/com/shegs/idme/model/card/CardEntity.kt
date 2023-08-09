package com.shegs.idme.model.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shegs.idme.utils.DateConverter
import java.time.LocalDateTime

@Entity(tableName = "card_table")
@TypeConverters(DateConverter::class)
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "card_name") val cardName: String,
    @ColumnInfo(name = "createdAt") val createdAt: LocalDateTime?
)
