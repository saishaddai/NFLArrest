package com.saishaddai.nflarrest.model

import com.google.gson.annotations.SerializedName

class NFArrestModels {

    data class Team(
        @SerializedName("Team") val team: String,
        @SerializedName("Team_name") val teamName: String,
        @SerializedName("Team_city") val teamCity: String,
        @SerializedName("arrest_count") val arrestCount: String
    )

    data class Player(
        @SerializedName("Name") val name: String,
        @SerializedName("arrest_count")val arrestCount: String
    )

    data class Crime(
        @SerializedName("Category") val category: String,
        @SerializedName("arrest_count")val arrestCount: String
    )

    data class Position(
        @SerializedName("Position") val position: String,
        @SerializedName("arrest_count")val arrestCount: String
    )

    data class Timeline(
        @SerializedName("Month") val month: String,
        @SerializedName("Year") val year: String,
        @SerializedName("arrest_count")val arrestCount: String
    )
}
