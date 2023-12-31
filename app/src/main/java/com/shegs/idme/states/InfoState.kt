package com.shegs.idme.states

import android.graphics.Bitmap
import java.util.Date

data class InfoState(
    var isAddingInfo: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val emailAddress: String = "",
    val homeAddress: String = "",
    val phoneNumber: String = "",
    val instagramHandle: String = "",
    val twitterHandle: String = "",
    val bio: String = "",
    val hobbies: String = "",
    val bankAccountName: String = "",
    val bankAccountNumber: String = "",
    val bankName: String = "",
    val qrcodeImage: Bitmap? = null,
    val createdAt: Date
)
