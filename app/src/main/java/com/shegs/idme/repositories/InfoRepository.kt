package com.shegs.idme.repositories

import com.shegs.idme.model.info.InfoDAO
import com.shegs.idme.model.info.InfoEntity
import javax.inject.Inject

class InfoRepository @Inject constructor(private val infoDAO: InfoDAO) {

    suspend fun insertInfo(info: InfoEntity){
        infoDAO.insertInfo(info)
    }

    suspend fun getInfoByInfoId(infoId: Int): InfoEntity? {
        return infoDAO.getInfoByInfoId(infoId)
    }
}