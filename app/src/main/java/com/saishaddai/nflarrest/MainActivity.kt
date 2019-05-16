package com.saishaddai.nflarrest

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.saishaddai.nflarrest.retrofit.NFLArrestAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.saishaddai.nflarrest.model.NFArrestModels
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var nflArrestAPI: NFLArrestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://nflarrest.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        //

        nflArrestAPI = retrofit.create(NFLArrestAPI::class.java)
        getCrimes()
    }

    private fun getCrimes() {
        val call: Call<List<NFArrestModels.Crime>> = nflArrestAPI.getTopCrimes(null, null, 5, null)

        call.enqueue(object : Callback<List<NFArrestModels.Crime>> {
            override fun onFailure(call: Call<List<NFArrestModels.Crime>>, t: Throwable) {
                setupNoConnectionMessage(t.message)
            }

            override fun onResponse(call: Call<List<NFArrestModels.Crime>>, response: Response<List<NFArrestModels.Crime>>) {
                if (response.isSuccessful) {
                    val crimeList = response.body() ?: ArrayList()
                    setupChart(crimeList)
                } else
                    setupNoConnectionMessage(null)
            }

        })
    }

    private fun setupNoConnectionMessage(errorMessage: String?) {
        textErrorMessage.visibility = View.VISIBLE
        crimeChart.visibility = View.GONE
        if(errorMessage == null)
            textErrorMessage.text = getString(R.string.connection_error)
        else
            textErrorMessage.text = getString(R.string.connection_error_detail, errorMessage)
    }

    private fun setupChart(crimeList: List<NFArrestModels.Crime>) {
        textErrorMessage.visibility = View.GONE
        crimeChart.visibility = View.VISIBLE

        val shortCrimeList: List<NFArrestModels.Crime> = cutCrimeList(crimeList)


        val pieEntries = mutableListOf<PieEntry>()
        for(crime in shortCrimeList) {
            pieEntries.add(PieEntry(crime.arrestCount.toFloat(), crime.category))
        }

        val pieDataSet = PieDataSet(pieEntries, getString(R.string.chart_title_top_crimes))
        pieDataSet.colors = GREY_COLORS
        val pieData = PieData(pieDataSet)

        Log.d(TAG, "setting values in the chart")
        crimeChart.data = pieData
        crimeChart.animateY(1000)
        crimeChart.invalidate()

    }


    private fun cutCrimeList(crimeList: List<NFArrestModels.Crime>): List<NFArrestModels.Crime> {
//        val shortCrimeList = mutableListOf<NFArrestModels.Crime>()
//        for(i in 0 .. 10) {
//            shortCrimeList.add(crimeList[i])
//        }
//
//        return shortCrimeList
        return crimeList
    }

    companion object {
        private val COLORFUL_COLORS = mutableListOf(
            Color.rgb(193, 37, 82),
            Color.rgb(255, 102, 0),
            Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31),
            Color.rgb(179, 100, 53)
        )


        private val GREY_COLORS = mutableListOf(
            Color.rgb(193, 193, 193),
            Color.rgb(102, 102, 102),
            Color.rgb(199, 199, 199),
            Color.rgb(31, 31, 31),
            Color.rgb(53, 53, 53),
            Color.rgb(0, 0, 0),
            Color.rgb(22, 22, 22),
            Color.rgb(44, 44, 44),
            Color.rgb(130, 130, 130),
            Color.rgb(200, 200, 200)
        )

        private val TAG = MainActivity::class.java.simpleName

    }
}
