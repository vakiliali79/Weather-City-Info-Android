package com.alivakili.weather_city_info.mainActivity.dataShape


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "current")
    val current: Current? = Current(),
    @Json(name = "location")
    val location: Location? = Location()
)