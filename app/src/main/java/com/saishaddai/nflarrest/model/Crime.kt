package com.saishaddai.nflarrest.model

import com.google.gson.annotations.SerializedName

public data class Crime(
    @SerializedName("Category") val category: String,
    @SerializedName("arrest_count")val arrestCount: String
)