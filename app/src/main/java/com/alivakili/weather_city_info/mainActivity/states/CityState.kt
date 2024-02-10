package com.alivakili.weather_city_info.mainActivity.states

import com.alivakili.weather_city_info.mainActivity.dataShape.City

sealed class CityState {
    data class Success(val categories:List<City>):CityState()
}