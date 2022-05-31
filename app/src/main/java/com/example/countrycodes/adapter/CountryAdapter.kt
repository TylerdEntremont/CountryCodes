package com.example.countrycodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrycodes.databinding.CountryItemBinding
import com.example.countrycodes.databinding.HeaderItemBinding
import com.example.countrycodes.model.CountriesItem
import com.example.countrycodes.model.HeaderItem

class CountryAdapter (
    private val countries: MutableList<Any> = mutableListOf()
        ): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun setNewData(newCountries: List<Any>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (countries[position] is CountriesItem) return 0
        if (countries[position] is HeaderItem) return 1
        return 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

       if (viewType==0) return CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        else return HeaderViewHolder(
           HeaderItemBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false)
       )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position)==0)(holder as CountryViewHolder).bind(countries[position] as CountriesItem)
        else (holder as HeaderViewHolder).bind(countries[position] as HeaderItem)
    }

    override fun getItemCount():Int = countries.size
}

class HeaderViewHolder (
    private val binding : HeaderItemBinding
        ): RecyclerView.ViewHolder(binding.root){

            fun bind (header:HeaderItem){
                binding.letter.text = header.letter
            }

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