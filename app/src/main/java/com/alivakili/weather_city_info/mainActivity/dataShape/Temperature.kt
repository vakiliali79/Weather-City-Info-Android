package com.alivakili.weather_city_info.mainActivity.dataShape

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    val temperature:String,
    val uv: String,
    val humidity:String,
    val cloudiness: String,
    val condition:String,


): Parcelable {
    constructor(dto:Current):this(
        temperature=dto?.tempC.toString(),
        uv= dto.uv.toString(),
        humidity=dto.humidity.toString(),
        cloudiness=dto.cloud.toString(),
        condition=dto.condition.toString()


    )
}