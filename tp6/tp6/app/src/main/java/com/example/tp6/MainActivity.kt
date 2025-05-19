package com.example.tp6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tp6.adapters.CityAdapter
import com.example.tp6.databinding.ActivityMainBinding
import data.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CityAdapter.onCityClickListener {
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val cities = arrayOf("Tunis", "Paris", "London", "Liverpool", "Ottawa", "Fargo", "Vegas")
    private lateinit var binding: ActivityMainBinding


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val weatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyler.adapter = CityAdapter(cities, this)
    }

    override fun onCityClick(city: String) {
        getWeatherData(city)
    }

    private fun getWeatherData(city: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = weatherApiService.getCurrentWeather(city)
                withContext(Dispatchers.Main) {
                    binding.temp.text = response.main.temp.toString()
                    binding.hum.text = response.main.humidity.toString()
                    binding.vent.text = response.wind.speed.toString()
                    binding.pays.text = response.sys.country
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Ville introuvable",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}