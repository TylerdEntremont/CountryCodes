package com.example.countrycodes.network

import com.example.countrycodes.model.CountriesItem
import retrofit2.Response

interface DataRepository {

    suspend fun getCountries(): Response<List<CountriesItem>>

}

class DataRepositoryImpl(
    private val service:NetworkServices = Service.networkService
):DataRepository {
  override suspend fun getCountries(): Response<List<CountriesItem>> =
    service.getCountries()
}
