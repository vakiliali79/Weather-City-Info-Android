package com.alivakili.weather_city_info.mainActivity.temperature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alivakili.weather_city_info.R
import com.alivakili.weather_city_info.databinding.ActivityTemperatureBinding
import com.alivakili.weather_city_info.mainActivity.dataShape.Temperature
import com.alivakili.weather_city_info.mainActivity.states.TemperatureState
import kotlinx.coroutines.launch

class TemperatureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemperatureBinding
    private lateinit var cityName:String
    private val viewModel: TemperatureViewModel by viewModels{
        TemperatureViewModel.factory(cityName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCityNameFromIntent()
        configureToolbar(title= cityName)
        observeState()
    }

    private fun getCityNameFromIntent(){
        cityName=getString(intent.getIntExtra("CITY_NAME",0))

    }

    private fun configureToolbar(title:String){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            this.title=title
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)

        }

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    private fun showErrorMessage(){
        Toast.makeText(this,"Unable to retrieve data!", Toast.LENGTH_LONG).show()
    }
    private fun showProgressBar(){
        binding.progressBar.visibility= View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility= View.GONE
    }

    private fun showTemperature(temperature: Temperature){
        hideProgressBar()
        when(temperature.condition){
            "Cloudy"->binding.background.setBackgroundResource(R.drawable.background_cloudy)
            "Partly cloudy"->binding.background.setBackgroundResource(R.drawable.background_cloudy)
            "Sunny"->binding.background.setBackgroundResource(R.drawable.background_sunny)
            "Rainy"->binding.background.setBackgroundResource(R.drawable.background_rainy)
            "Light rain"->binding.background.setBackgroundResource(R.drawable.background_rainy)


            else->{
                binding.background.setBackgroundResource(R.drawable.background_night)
                binding.conditionTextView.text="No text"
            }
        }

        binding.conditionTextView.text=temperature.condition
        binding.temperatureTextView.text=temperature.temperature
        binding.cloudinessTextView.text=temperature.cloudiness+"%"
        binding.uvIndexTextView.text=temperature.uv
        binding.humidityTextView.text=temperature.humidity+"%"


    }

    private fun observeState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.state.collect{
                        state->
                    when(state){
                        TemperatureState.Loading -> showProgressBar()
                        is TemperatureState.Success -> {
                            showTemperature(state.temperature)
                        }
                        TemperatureState.Failure -> showErrorMessage()
                        else -> {
                            showErrorMessage()
                        }
                    }
                }
            }
        }
    }

}