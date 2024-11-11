package ru.netology.weatherforecastwithofflinemode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.netology.weatherforecastwithofflinemode.R
import ru.netology.weatherforecastwithofflinemode.datamodels.City


class CityAdapter(private val cities: List<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.cityName)
        val cityTemperature: TextView = itemView.findViewById(R.id.cityTemperature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.cityName.text = city.name
        holder.cityTemperature.text = "${city.temperature} °C"
    }

    override fun getItemCount(): Int = cities.size
}