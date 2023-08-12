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

    fun fetchInfoById(infoId: Int){
        viewModelScope.launch {
            val info = infoRepository.getInfoByInfoId(infoId)
            _getInfoById.value = info
        }
    }

    fun onEvent(event: InfoEvents){
        when (event){
            is InfoEvents.SaveUserInput -> {
                val userInfo = event.userInput

                // Convert Bitmap to ByteArray
                val outputStream = ByteArrayOutputStream()
                userInfo.qrCodeImageBitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val qrCodeImage = outputStream.toByteArray()


                val information = InfoEntity(
                    firstName = userInfo.firstName,
                    lastName = userInfo.lastName,
                    emailAddress = userInfo.emailAddress,
                    homeAddress = userInfo.homeAddress,
                    phoneNumber = userInfo.phoneNumber,
                    instagramHandle = userInfo.instagramHandle,
                    twitterHandle = userInfo.twitterHandle,
                    bio = userInfo.bio,
                    hobbies = userInfo.hobbies,
                    bankAccountName = userInfo.bankAccountName,
                    bankAccountNumber = userInfo.bankAccountNumber,
                    bankName = userInfo.bankName,
                    qrCodeImage = qrCodeImage,
                    createdAt = LocalDateTime.now()
                )
                viewModelScope.launch {
                    infoRepository.insertInfo(information)
                    infoState.update { it.copy(isAddingInfo = false) }
                }
            }

            is InfoEvents.SetUserInput -> {
                val userInput = event.setInput
                infoState.update { it.copy(
                    firstName = userInput.firstName,
                    lastName = userInput.lastName,
                    emailAddress = userInput.emailAddress,
                    homeAddress = userInput.homeAddress,
                    phoneNumber = userInput.phoneNumber,
                    instagramHandle = userInput.instagramHandle,
                    twitterHandle = userInput.twitterHandle,
                    bio = userInput.bio,
                    hobbies = userInput.hobbies,
                    bankAccountName = userInput.bankAccountName,
                    bankAccountNumber = userInput.bankAccountNumber,
                    bankName = userInput.bankName,
                    qrcodeImage = userInput.qrCodeImageBitmap,
                    createdAt = userInput.createdAt
                )
                }
            }
        }
    }

}