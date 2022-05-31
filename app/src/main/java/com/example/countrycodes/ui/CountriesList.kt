package com.example.countrycodes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrycodes.adapter.CountryAdapter
import com.example.countrycodes.databinding.FragmentCountriesListBinding
import com.example.countrycodes.model.CountriesItem
import com.example.countrycodes.utils.ResponseState
import com.example.countrycodes.viewmodel.CountriesViewModel


class CountriesList : Fragment() {

    private val binding by lazy{
        FragmentCountriesListBinding.inflate(layoutInflater)
    }

    private val countriesViewModel by lazy {
        ViewModelProvider(requireActivity())[CountriesViewModel::class.java]
    }

    private val countryAdapter by lazy{
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.countriesRv.apply{
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter=countryAdapter
        }

        countriesViewModel.countries.observe(viewLifecycleOwner){ state ->
            when (state) {

                is ResponseState.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Success<*> -> {
                    val data = state.response as List<Any>
                    countryAdapter.setNewData(data)

                }
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(), "Error happened ${state.error.localizedMessage}", Toast.LENGTH_LONG).show()
                    Log.e("SF", "onCreateView: "+ state.error.localizedMessage, state.error)
                }
            }

        }

        countriesViewModel.getCountries()


        // Inflate the layout for this fragment
        return binding.root
    }

}