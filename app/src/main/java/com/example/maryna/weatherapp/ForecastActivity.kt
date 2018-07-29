package com.example.maryna.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        //println(searchTerm)

        var retriver = WeatherRetriver()

        val callback = object : Callback<Weather>{

            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("It's working")

                title = response?.body()?.query?.results?.channel?.title

                //println(response?.body()?.query?.results?.channel?.item?.forecast)

                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()
                if (forecasts != null){
                    for (forecast in forecasts){
                        val newString = "${forecast.date} - High:${forecast.high} Low:${forecast.low} - ${forecast.text}"
                        forecastStrings.add(newString)
                    }
                }

                var listView = findViewById<ListView>(R.id.forecastListView)

                var randomList = listOf("Good weather!", "Nice weather", "The weather will be nice tomorrow")

                var adapter = ArrayAdapter(this@ForecastActivity,android.R.layout.simple_list_item_1,forecastStrings)
                listView.adapter = adapter
            }
            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("It's not working")
            }

//            override fun onResponse(call: Call<List<Forecast>>?, response: Response<List<Forecast>>?) {
//                println("We got a responce")
//                println(response?.body())
//
//                for (forecastDay in response!!.body()!!){
//                    println("High:${forecastDay.high} Low:${forecastDay.low}")
//                }
//            }
//            override fun onFailure(call: Call<List<Forecast>>?, t: Throwable?) {
//                println("It failed :(")
//            }

        }

        val searchTerm = intent.extras.getString("searchTerm")
        retriver.getForecast(callback, searchTerm)
    }
}
