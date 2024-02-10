package com.alivakili.weather_city_info.mainActivity.dataShape


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Current(
    @Json(name = "cloud")
    val cloud: Int? = 0,
    @Json(name = "condition")
    val condition: Condition? = Condition(),
    @Json(name = "humidity")
    val humidity: Int? = 0,
    @Json(name = "is_day")
    val isDay: Int? = 0,
    @Json(name = "temp_c")
    val tempC: Double? = 5.0,
    @Json(name = "uv")
    val uv: Double? = 0.0
)