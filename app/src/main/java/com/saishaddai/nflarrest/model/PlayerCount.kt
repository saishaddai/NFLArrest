package com.saishaddai.nflarrest.model

import com.google.gson.annotations.SerializedName

data class PlayerCount(
    @SerializedName("Name") val name: String,
    @SerializedName("arrest_count")val arrestCount: String
)