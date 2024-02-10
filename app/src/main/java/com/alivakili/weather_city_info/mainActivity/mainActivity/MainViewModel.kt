package com.alivakili.weather_city_info.mainActivity.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alivakili.weather_city_info.R
import com.alivakili.weather_city_info.mainActivity.dataShape.City
import com.alivakili.weather_city_info.mainActivity.states.CityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel :ViewModel(){
    private val _state= MutableStateFlow<CityState>(CityState.Success(cities()))
    var state: StateFlow<CityState> =_state

    companion object{
        fun factory(): ViewModelProvider.Factory{
            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel() as T
                }
            }
        }
    }

    fun cities(): List<City> {
        return listOf(
            City(name = R.string.agra, image = R.drawable.agra),
            City(name = R.string.Barcelona, image = R.drawable.barcelona),
            City(name = R.string.beijing, image = R.drawable.beijing),
            City(name = R.string.sydney, image = R.drawable.sydney),
            City(name = R.string.paris, image = R.drawable.paris),
            City(name = R.string.new_york, image = R.drawable.new_york),
            City(name = R.string.london, image = R.drawable.london),
            City(name = R.string.rio_de_janeiro, image = R.drawable.rio_de_janeiro),
            City(name = R.string.moscow, image = R.drawable.moscow),
            City(name = R.string.rome, image = R.drawable.rome),
            )
    }
}