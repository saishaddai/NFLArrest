package com.saishaddai.nflarrest.retrofit

import com.saishaddai.nflarrest.model.NFArrestModels
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Check documentation at http://nflarrest.com/api/
 * General query for most of the calls
 * start_date 2000-01-3 Filter data by start date
 * end_date 2016-01-31 Filter data by end date
 * limit integer Limit results to a specific number
 */
interface NFLArrestAPI {

    /**
     * Example http://nflarrest.com/api/v1/crime
     * start_pos offset for limit, for pagination
     */
    @GET("crime")
    fun getTopCrimes(@Query("start_date") startDate: String?,
                     @Query("end_date") endDate: String?,
                     @Query("limit") limit: Int?,
                     @Query("start_pos") startPos: Int?) : Call<List<NFArrestModels.Crime>>

    // Second version of getTop Crime using Singler class to make ot match with an RX Java observable
    @GET("crime")
    fun getTopCrimes2(@Query("start_date") startDate: String?,
                     @Query("end_date") endDate: String?,
                     @Query("limit") limit: Int?,
                     @Query("start_pos") startPos: Int?) : Single<List<NFArrestModels.Crime>>



    @GET("crime/topPlayers/{crimeId}")
    fun getTopPlayersForCrime(@Path("crimeId") crime: String,
                              @Query("start_date") startDate: String?,
                              @Query("end_date") endDate: String?,
                              @Query("limit") limit: Int?): Call<List<NFArrestModels.Player>>


    @GET("crime/topTeams/{crimeId}")
    fun getTopTeamsForCrime(@Path("crimeId") crime: String,
                              @Query("start_date") startDate: String?,
                              @Query("end_date") endDate: String?,
                              @Query("limit") limit: Int?): Call<List<NFArrestModels.Team>>


    @GET("crime/topPositions/{crimeId}")
    fun getTopPositionsforCrime(@Path("crimeId") crime: String,
                            @Query("start_date") startDate: String?,
                            @Query("end_date") endDate: String?,
                            @Query("limit") limit: Int?): Call<List<NFArrestModels.Position>>

    @GET("crime/timeline/{crimeId}")
    fun getTimeline(@Path("crimeId") crime: String,
                                @Query("start_date") startDate: String?,
                                @Query("end_date") endDate: String?,
                                @Query("limit") limit: Int?): Call<List<NFArrestModels.Timeline>>


}