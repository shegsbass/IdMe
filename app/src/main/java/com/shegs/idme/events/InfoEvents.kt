package com.shegs.idme.events

import android.graphics.Bitmap
import java.util.Date

sealed interface InfoEvents {

    object SaveInfo: InfoEvents
    data class SetFirstName(val firstName: String): InfoEvents
    data class SetLastName(val lastName: String): InfoEvents
    data class SetEmailAddress(val emailAddress: String): InfoEvents
    data class SetHomeAddress(val homeAddress: String): InfoEvents
    data class SetPhoneNumber(val phoneNumber: Int): InfoEvents
    data class SetInstagramHandle(val instagramHandle: String): InfoEvents
    data class SetTwitterHandle(val twitterHandle: String): InfoEvents
    data class SetBio(val bio: String): InfoEvents
    data class SetHobbies(val hobbies: String): InfoEvents
    data class SetBankAccountName(val bankAccountName: String): InfoEvents
    data class SetBankAccountNumber(val bankAccountNumber: Int): InfoEvents
    data class SetBankName(val bankName: String): InfoEvents
    data class GeneratedQRCOde(val qrCodeImage: Bitmap): InfoEvents
    data class CreatedAt(val createdAt: Date): InfoEvents

}