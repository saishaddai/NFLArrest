package com.saishaddai.nflarrest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.saishaddai.nflarrest.retrofit.NFLArrestAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.saishaddai.nflarrest.model.NFArrestModels


class MainActivity : AppCompatActivity() {
    lateinit var nflArrestAPI: NFLArrestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        text_view_result
        val retrofit = Retrofit.Builder()
            .baseUrl("http://nflarrest.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        nflArrestAPI = retrofit.create(NFLArrestAPI::class.java)
        getCrimes()
//        getPlayersForCrime("Drugs")
//        getPlayersForCrime("DUI")
//        getPlayersForCrime("Domestic violence")
//        getPlayersForCrime("Assault")
//        getPlayersForCrime("Disorderly conduct")
    }

//    private fun getPlayersForCrime(crimeId: String) {
//        val call: Call<List<PlayerCount>> = nflArrestAPI.getTopPlayersForCrime(crimeId)
//
//        call.enqueue(object : Callback<List<PlayerCount>> {
//            override fun onFailure(call: Call<List<PlayerCount>>, t: Throwable) {
//                //text_view_result.text = t.message
//            }
//
//            override fun onResponse(call: Call<List<PlayerCount>>, response: Response<List<PlayerCount>>) {
//                if (response.isSuccessful) {
//                    val playerList = response.body() ?: ArrayList()
//                    playerList.filter { it.name.startsWith("A") }
//
//                    val pie = AnyChart.pie()
//
//                    val data = mutableListOf<DataEntry>()
//                    for(player in playerList) {
//                        data.add(ValueDataEntry(player.name, Integer.valueOf(player.arrestCount)))
//                    }
//
//                    pie.data(data)
//
//                    any_chart_view.setChart(pie)
//                } else
//                    any_chart_view.setChart(null)
//                    //text_view_result.text = "Error with code: " + response.code()
//            }
//
//        })
//    }

    private fun getCrimes() {
        val call: Call<List<NFArrestModels.Crime>> = nflArrestAPI.getTopCrimes(null, null, null, null)

        call.enqueue(object : Callback<List<NFArrestModels.Crime>> {
            override fun onFailure(call: Call<List<NFArrestModels.Crime>>, t: Throwable) {
                text_view_result.text = t.message
            }

            override fun onResponse(call: Call<List<NFArrestModels.Crime>>, response: Response<List<NFArrestModels.Crime>>) {
                if (response.isSuccessful) {
                    val crimeList = response.body() ?: ArrayList()
                    for (crime in crimeList) {
                        var content = ""
                        content += "crime: " + crime.category + "\n"
                        content += "count: " + crime.arrestCount + "\n\n"
                        text_view_result.append(content)
                    }
                } else
                    text_view_result.text = "Error with code: " + response.code()
            }

        })
    }
}
