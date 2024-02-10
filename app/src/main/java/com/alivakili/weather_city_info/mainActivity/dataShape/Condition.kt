package com.alivakili.weather_city_info.mainActivity.dataShape


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(
    @Json(name = "text")
    val text: String? = ""
)