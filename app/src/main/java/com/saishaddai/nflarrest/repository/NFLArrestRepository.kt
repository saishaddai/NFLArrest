package com.saishaddai.nflarrest.repository

import com.saishaddai.nflarrest.model.NFArrestModels

class NFLArrestRepository {

    fun getAllCrimesCount(varargs: String) : List<NFArrestModels.Crime> {
        return listOf(NFArrestModels.Crime("DUI", "5"))
    }

}