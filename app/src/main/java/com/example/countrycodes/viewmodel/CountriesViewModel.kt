package com.example.countrycodes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
                                                _countries.postValue(ResponseState.Success(it))
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
