package com.example.countrycodes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrycodes.model.HeaderItem
import com.example.countrycodes.network.DataRepository
import com.example.countrycodes.network.DataRepositoryImpl
import com.example.countrycodes.utils.ResponseState
import kotlinx.coroutines.*

class CountriesViewModel (
        private val countriesRepository: DataRepository = DataRepositoryImpl(),
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
        private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)

) : ViewModel(), CoroutineScope by coroutineScope {

        private val _countries: MutableLiveData<ResponseState> = MutableLiveData()
        val countries: LiveData<ResponseState> get() = _countries

        fun getCountries(){
                launch{
                        try {
                                val mResponse = countriesRepository.getCountries()
                                if (mResponse.isSuccessful) {
                                        mResponse.body()?.let {
                                                val countries = it.sortedBy { it.name }
                                                val countriesWithHeaders = mutableListOf<Any>()
                                                var lastLetter=Char.MIN_VALUE
                                                for (country in countries){
                                                        if (country.name[0]>lastLetter){
                                                                countriesWithHeaders.add(HeaderItem(country.name[0].toString()))
                                                                lastLetter = country.name[0]
                                                        }
                                                        countriesWithHeaders.add(country)
                                                }
                                                _countries.postValue(ResponseState.Success(countriesWithHeaders))
                                        }
                                                ?: throw Exception("Response from countries is coming as null")
                                } else {
                                        throw Exception("Response from countries is a failure")
                                }
                        }catch (e:Exception){
                                _countries.postValue(ResponseState.Error(e))
                        }
                }
        }

}
