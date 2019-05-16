package com.saishaddai.nflarrest.retrofit

import android.util.Log
import com.saishaddai.nflarrest.model.NFArrestModels
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NFLArrestRepository {

    private val api: NFLArrestAPI

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NFLArrestAPI::class.java)
        Log.d(TAG, "API class instanced")
    }

    fun getTopCrimes() : Single<List<NFArrestModels.Crime>> = api.getTopCrimes2(null, null, null, null)

    companion object {
        private val TAG = NFLArrestRepository::class.java.simpleName
        private const val BASE_URL = "http://nflarrest.com/api/v1/"


    }
}



