package pe.edu.upc.meals.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("filter.php")
    fun getMeals(@Query("c") category: String): Call<MealResponse>
}