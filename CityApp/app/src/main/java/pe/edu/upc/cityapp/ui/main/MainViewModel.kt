package pe.edu.upc.cityapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.cityapp.data.entities.City
import pe.edu.upc.cityapp.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _apiKey = MutableLiveData<String>()
    private val _limit = MutableLiveData<String>()

    private val cities: MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>().also {
            searchCities()
        }
    }

    fun start(name: String, apiKey: String, limit: String) {
        _name.value = name
        _apiKey.value = apiKey
        _limit.value = limit
    }

    fun getCities(): LiveData<List<City>> {
        return cities
    }

    fun searchCities() {
        val searchCities = ApiClient.build()
            .searchCities(_name.value.toString(), _limit.value.toString(), _apiKey.value.toString())

        searchCities.enqueue(object : Callback<MutableList<City>> {
            override fun onResponse(
                call: Call<MutableList<City>>,
                response: Response<MutableList<City>>
            ) {
                if (response.isSuccessful) {
                    cities.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MutableList<City>>, t: Throwable) {
                Log.d("MainViewModel", t.toString())
            }

        })

    }


}