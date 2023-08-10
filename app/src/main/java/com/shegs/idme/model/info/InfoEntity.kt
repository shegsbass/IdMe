package com.shegs.idme.model.info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shegs.idme.utils.DateConverter
import java.time.LocalDateTime

@Entity(tableName = "information_table")
@TypeConverters(DateConverter::class)
data class InfoEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "info_id") val infoId: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "email_address") val emailAddress: String?,
    @ColumnInfo(name = "home_address") val homeAddress: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: Int?,
    @ColumnInfo(name = "instagram_handle") val instagramHandle: String?,
    @ColumnInfo(name = "twitter_handle") val twitterHandle: String?,
    @ColumnInfo(name = "bio") val bio: String?,
    @ColumnInfo(name = "hobbies") val hobbies: String?,
    @ColumnInfo(name = "bank_account_name") val bankAccountName: String?,
    @ColumnInfo(name = "bank_account_number") val bankAccountNumber: Int?,
    @ColumnInfo(name = "bank_name") val bankName: String?,

    @ColumnInfo(name = "qrcode_image") val qrCodeImage: ByteArray,
    @ColumnInfo(name = "createdAt") val createdAt: LocalDateTime?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InfoEntity

        if (!qrCodeImage.contentEquals(other.qrCodeImage)) return false

        return true
    }

    override fun hashCode(): Int {
        return qrCodeImage.contentHashCode()
    }
}
