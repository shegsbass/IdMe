package com.shegs.idme.events

import android.graphics.Bitmap
import java.util.Date

sealed interface InfoEvents {

    data class SaveUserInput(val userInput: UserInfo): InfoEvents

    data class UserInfo(
        val firstName: String,
        val lastName: String,
        val emailAddress: String,
        val homeAddress: String,
        val phoneNumber: Int,
        val instagramHandle: String,
        val twitterHandle: String,
        val bio: String,
        val hobbies: String,
        val bankAccountName: String,
        val bankAccountNumber: Int,
        val bankName: String,
        val qrCodeImageBitmap: Bitmap?
    )

    data class SetUserInput(val setInput: UserInput): InfoEvents

    data class UserInput(
        val firstName: String,
        val lastName: String,
        val emailAddress: String,
        val homeAddress: String,
        val phoneNumber: Int,
        val instagramHandle: String,
        val twitterHandle: String,
        val bio: String,
        val hobbies: String,
        val bankAccountName: String,
        val bankAccountNumber: Int,
        val bankName: String,
        val qrCodeImageBitmap: Bitmap?,
        val createdAt: Date
    )

}