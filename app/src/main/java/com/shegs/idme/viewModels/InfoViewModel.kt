package com.shegs.idme.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegs.idme.model.info.InfoEntity
import com.shegs.idme.repositories.InfoRepository
import com.shegs.idme.states.InfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    init {
        fetchInfoById()
    }

    private fun fetchInfoById(infoId: Int){
        viewModelScope.launch {
            val info = infoRepository.getInfoByInfoId(infoId)
            _getInfoById.value = info
        }

    }

}