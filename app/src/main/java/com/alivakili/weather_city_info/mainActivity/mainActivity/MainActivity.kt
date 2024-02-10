package com.alivakili.weather_city_info.mainActivity.mainActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.alivakili.weather_city_info.databinding.ActivityMainBinding
import com.alivakili.weather_city_info.mainActivity.temperature.TemperatureActivity
import com.alivakili.weather_city_info.mainActivity.dataShape.City
import com.alivakili.weather_city_info.mainActivity.states.CityState
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels{
        MainViewModel.factory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeState()
    }


    private fun observeState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.state.collect{
                        state->
                    when(state){
                        is CityState.Success -> showCities(state.categories)
                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun showCities(categories:List<City>) {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = CityRecyclerViewAdapter(items =categories , onClicked = ::showTemperature)
            setHasFixedSize(true)
        }
    }

    private fun showTemperature(cityName:Int){

        val intent= Intent(this, TemperatureActivity::class.java)
        intent.putExtra("CITY_NAME",cityName)
        startActivity(intent)
    }




}