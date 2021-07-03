package pe.edu.upc.cityapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://api.openweathermap.org/geo/1.0/"
    private var apiService: ApiService? = null

    fun build(): ApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        return apiService as ApiService

    }
}