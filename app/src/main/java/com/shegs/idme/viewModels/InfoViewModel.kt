package com.shegs.idme.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegs.idme.events.InfoEvents
import com.shegs.idme.model.info.InfoEntity
import com.shegs.idme.repositories.InfoRepository
import com.shegs.idme.states.InfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val infoRepository: InfoRepository
): ViewModel() {
    private val currentDate = Date()
    private val _state = MutableStateFlow(InfoState(createdAt = currentDate))
    val infoState = _state

    private val _getInfoById = MutableStateFlow<InfoEntity?>(null)
     val getInfoById: StateFlow<InfoEntity?> = _getInfoById

    private fun fetchInfoById(infoId: Int){
        viewModelScope.launch {
            val info = infoRepository.getInfoByInfoId(infoId)
            _getInfoById.value = info
        }
    }

    fun onEvent(event: InfoEvents){
        when (event){
            is InfoEvents.SaveInfo -> {
                val firstName = infoState.value.firstName
                val lastName = infoState.value.lastName
                val email = infoState.value.emailAddress
                val homeAddress = infoState.value.homeAddress
                val phoneNumber = infoState.value.phoneNumber
                val instagramHandle = infoState.value.instagramHandle
                val twitterHandle = infoState.value.twitterHandle
                val bio = infoState.value.bio
                val hobbies = infoState.value.hobbies
                val bankAccountName = infoState.value.bankAccountName
                val bankAccountNumber = infoState.value.bankAccountNumber
                val bankName = infoState.value.bankName
                val qrCodeImageBitmap = infoState.value.qrcodeImage

                // Convert Bitmap to ByteArray
                val outputStream = ByteArrayOutputStream()
                qrCodeImageBitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val qrCodeImage = outputStream.toByteArray()


                val information = InfoEntity(
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = email,
                    homeAddress = homeAddress,
                    phoneNumber = phoneNumber,
                    instagramHandle = instagramHandle,
                    twitterHandle = twitterHandle,
                    bio = bio,
                    hobbies = hobbies,
                    bankAccountName = bankAccountName,
                    bankAccountNumber = bankAccountNumber,
                    bankName = bankName,
                    qrCodeImage = qrCodeImage,
                    createdAt = LocalDateTime.now()
                )
                viewModelScope.launch {
                    infoRepository.insertInfo(information)
                    infoState.update { it.copy(isAddingInfo = false) }
                }
            }

            is InfoEvents.SetFirstName -> {
                infoState.update { it.copy(firstName = event.firstName) }
            }

            is InfoEvents.SetLastName -> {
                infoState.update { it.copy(lastName = event.lastName) }
            }

            is InfoEvents.SetEmailAddress -> {
                infoState.update { it.copy(emailAddress = event.emailAddress) }
            }

            is InfoEvents.SetHomeAddress -> {
                infoState.update { it.copy(homeAddress = event.homeAddress) }
            }

            is InfoEvents.SetPhoneNumber -> {
                infoState.update { it.copy(phoneNumber = event.phoneNumber) }
            }

            is InfoEvents.SetInstagramHandle -> {
                infoState.update { it.copy(instagramHandle = event.instagramHandle) }
            }

            is InfoEvents.SetTwitterHandle -> {
                infoState.update { it.copy(twitterHandle = event.twitterHandle) }
            }

            is InfoEvents.SetBio -> {
                infoState.update { it.copy(bio = event.bio) }
            }

            is InfoEvents.SetHobbies -> {
                infoState.update { it.copy(hobbies = event.hobbies) }
            }

            is InfoEvents.SetBankAccountName -> {
                infoState.update { it.copy(bankAccountName = event.bankAccountName) }
            }

            is InfoEvents.SetBankAccountNumber -> {
                infoState.update { it.copy(bankAccountNumber = event.bankAccountNumber) }
            }

            is InfoEvents.SetBankName -> {
                infoState.update { it.copy(bankName = event.bankName) }
            }

            is InfoEvents.GeneratedQRCOde -> {
                infoState.update { it.copy(qrcodeImage = event.qrCodeImage) }
            }

            is InfoEvents.CreatedAt -> {
                infoState.update { it.copy(createdAt = event.createdAt) }
            }

        }
    }

}