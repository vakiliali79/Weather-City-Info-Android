package com.alivakili.weather_city_info.mainActivity.temperature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alivakili.weather_city_info.mainActivity.api.RetrofitClient
import com.alivakili.weather_city_info.mainActivity.dataShape.Temperature
import com.alivakili.weather_city_info.mainActivity.dataShape.Weather
import com.alivakili.weather_city_info.mainActivity.states.TemperatureState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TemperatureViewModel(private val cityName: String) : ViewModel() {
    private var _state = MutableStateFlow<TemperatureState>(TemperatureState.Loading)
    var state: StateFlow<TemperatureState> = _state

    init {
        retrieveWeather()
    }

    private fun retrieveWeather() {

        val call = RetrofitClient.ApiClient.apiService.currentWeather(apikey = "8e5eac5f9be345ebbd4101118230711", cityName=cityName)


        if (call != null) {
            call.enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (response.isSuccessful) {
                        val weather=response.body()
                        val temperature = Temperature(
                            temperature = weather?.current?.tempC.toString(),
                            uv=weather?.current?.uv.toString(),
                            humidity = weather?.current?.humidity.toString(),
                            cloudiness = weather?.current?.cloud.toString(),
                            condition = weather?.current?.condition?.text.toString()
                        )
                        _state.value=TemperatureState.Success(
                            temperature
                        )
                    } else {
                        _state.value=TemperatureState.Failure
                        retrieveWeather()
                    }
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    _state.value=TemperatureState.Failure
                    Log.e("TAG", "onFailure: "+t.message, )
                    retrieveWeather()
                }
            })
        }

    }



    companion object {
        fun factory(cityName: String?): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return cityName?.let { TemperatureViewModel(it) } as T
                }
            }
        }
    }


}