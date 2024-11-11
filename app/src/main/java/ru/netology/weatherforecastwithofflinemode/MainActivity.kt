package ru.netology.weatherforecastwithofflinemode

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.netology.weatherforecastwithofflinemode.APIinterface.WeatherApi
import ru.netology.weatherforecastwithofflinemode.dao.CityDao
import ru.netology.weatherforecastwithofflinemode.datamodels.City
import ru.netology.weatherforecastwithofflinemode.datamodels.WeatherDatabase
import ru.netology.weatherforecastwithofflinemode.datamodels.WeatherResponse

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cityDao: CityDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = WeatherDatabase.getDatabase(this)
        cityDao = db.cityDao()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadCities()

        findViewById<Button>(R.id.addCityButton).setOnClickListener {
            // Логика добавления города
        }
    }

    private fun loadCities() {
        lifecycleScope.launch {
            cityDao.getAllCities()
            // Обновите адаптер для отображения городов
        }
    }

    private fun fetchWeather(city: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApi::class.java)
        val call = weatherApi.getWeather(city, "YOUR_API_KEY")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data class City(
                            val id: Int,
                            val name: String,
                            val country: String,
                            val lat: Double,
                            val lon: Double
                        )

                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Обработка ошибки
            }
        })
    }

    private fun insertCity(city: City) {
        lifecycleScope.launch {
            cityDao.insert(city)
            loadCities()
        }
    }
}