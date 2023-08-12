package com.shegs.idme.model.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shegs.idme.model.info.InfoEntity
import com.shegs.idme.utils.DateConverter
import java.time.LocalDateTime

@Entity(
    tableName = "card_table",
    foreignKeys = [
        ForeignKey(
            entity = InfoEntity::class,
            parentColumns = ["info_id"],
            childColumns = ["info_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

@TypeConverters(DateConverter::class)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")val cardId: Int = 0,
    @ColumnInfo(name = "card_name") val cardName: String,
    @ColumnInfo(name = "info_id") val infoId: Int,
    @ColumnInfo(name = "createdAt") val createdAt: LocalDateTime?,
)
