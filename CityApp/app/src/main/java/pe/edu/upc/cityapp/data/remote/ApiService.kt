package pe.edu.upc.cityapp.data.remote

import pe.edu.upc.cityapp.data.entities.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("direct")
    fun searchCities(
        @Query("q") query: String,
        @Query("limit") limit: String,
        @Query("appid") apiKey: String
    ): Call<MutableList<City>>
}

