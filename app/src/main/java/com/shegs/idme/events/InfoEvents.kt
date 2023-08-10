package com.shegs.idme.events

import android.graphics.Bitmap
import java.util.Date

sealed interface InfoEvents {

    object SaveInfo: InfoEvents
    data class SetFirstName(val firstName: String): InfoEvents
    data class SetLastName(val lastName: String): InfoEvents
    data class SetEmailAddress(val firstName: String): InfoEvents
    data class SetHomeAddress(val firstName: String): InfoEvents
    data class SetPhoneNumber(val firstName: String): InfoEvents
    data class SetInstagramHandle(val firstName: String): InfoEvents
    data class SetTwitterHandle(val firstName: String): InfoEvents
    data class SetBio(val firstName: String): InfoEvents
    data class SetHobbies(val firstName: String): InfoEvents
    data class SetBankAccountName(val firstName: String): InfoEvents
    data class SetBankAccountNumber(val firstName: String): InfoEvents
    data class SetBankName(val firstName: String): InfoEvents
    data class GeneratedQRCOde(val qrCodeImage: Bitmap): InfoEvents
    data class CreatedAt(val createdAt: Date): InfoEvents

}