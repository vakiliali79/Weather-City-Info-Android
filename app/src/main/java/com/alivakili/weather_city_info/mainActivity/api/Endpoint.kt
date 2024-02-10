package com.alivakili.weather_city_info.mainActivity.api

import com.alivakili.weather_city_info.mainActivity.dataShape.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("current.json")
    fun currentWeather(
        @Query("key")apikey:String,
        @Query("q")cityName: String,


    ): Call<Weather>
}

