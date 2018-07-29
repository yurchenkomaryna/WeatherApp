  package com.example.maryna.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main_weather.view.*

  class MainWeather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_weather)

        var button_forecast = findViewById<Button>(R.id.button_forecast)
        button_forecast.setOnClickListener{
            var moveIntent = Intent(this, ForecastActivity::class.java)
            val searchEditText = findViewById<EditText>(R.id.searchEditText)
            moveIntent.putExtra("searchTerm", searchEditText.text.toString())
            startActivity(moveIntent)
        }
    }
}
