package com.alivakili.weather_city_info.mainActivity.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alivakili.weather_city_info.databinding.LayoutSelectCityCardBinding
import com.alivakili.weather_city_info.mainActivity.dataShape.City

class CityRecyclerViewAdapter(
    private val items: List<City>,
    private val onClicked: (Int) -> Unit,
) : RecyclerView.Adapter<CityRecyclerViewAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.create(parent, onClicked)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = items[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CityViewHolder(
        private val binding: LayoutSelectCityCardBinding, private val onClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.imageView.setBackgroundResource(city.image)
            binding.cardView.setOnClickListener {
                onClicked(city.name)
            }
            binding.imageView.setOnClickListener {
                onClicked(city.name)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onClicked: (Int) -> Unit): CityViewHolder {
                val binding = LayoutSelectCityCardBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return CityViewHolder(binding = binding, onClicked = onClicked)
            }
        }
    }
}



