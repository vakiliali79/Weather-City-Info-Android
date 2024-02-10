package com.alivakili.weather_city_info.mainActivity.states

import com.alivakili.weather_city_info.mainActivity.dataShape.Temperature


sealed class TemperatureState {
    object Loading: TemperatureState()
    data class Success(val temperature:Temperature): TemperatureState()
    object Failure: TemperatureState()
}