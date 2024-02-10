package com.alivakili.weather_city_info.mainActivity.dataShape


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "country")
    val country: String? = "",
    @Json(name = "lat")
    val lat: Double? = 0.0,
    @Json(name = "localtime")
    val localtime: String? = "",
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Int? = 0,
    @Json(name = "lon")
    val lon: Double? = 0.0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "region")
    val region: String? = "",
    @Json(name = "tz_id")
    val tzId: String? = ""
)