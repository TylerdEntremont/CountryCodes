package com.example.countrycodes.network

import com.example.countrycodes.model.CountriesItem
import retrofit2.Response
import retrofit2.http.GET

interface NetworkServices {

    @GET(COUNTRIES)
    suspend fun getCountries(): Response<List<CountriesItem>>


    companion object{
        //get countries https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json

        const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        private const val COUNTRIES ="countries.json"
    }
}