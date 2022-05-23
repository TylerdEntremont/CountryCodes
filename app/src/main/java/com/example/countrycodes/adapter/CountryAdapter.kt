package com.example.countrycodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrycodes.databinding.CountryItemBinding
import com.example.countrycodes.model.CountriesItem

class CountryAdapter (
    private val countries: MutableList<CountriesItem> = mutableListOf()
        ): RecyclerView.Adapter<CountryViewHolder>(){

    fun setNewData(newCountries: List<CountriesItem>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            CountryItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount():Int = countries.size
}

class CountryViewHolder (
    private val binding : CountryItemBinding
        ): RecyclerView.ViewHolder(binding.root){

            fun bind (country: CountriesItem){
                binding.countryName.text=country.name
                binding.region.text=country.region
                binding.code.text=country.code
                binding.capital.text=country.capital
            }
        }