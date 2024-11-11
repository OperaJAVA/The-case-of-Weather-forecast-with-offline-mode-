package ru.netology.weatherforecastwithofflinemode.APIinterface

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.weatherforecastwithofflinemode.datamodels.WeatherResponse

interface WeatherApi {
    @GET("weather")
    fun getWeather(@Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String = "metric"): Call<WeatherResponse>
}