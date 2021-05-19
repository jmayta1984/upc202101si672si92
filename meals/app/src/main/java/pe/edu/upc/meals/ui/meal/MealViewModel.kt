package pe.edu.upc.meals.ui.meal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.meals.data.entities.Meal
import pe.edu.upc.meals.data.remote.ApiClient
import pe.edu.upc.meals.data.remote.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : ViewModel() {
    private val _category = MutableLiveData<String>()
    private val meals: MutableLiveData<List<Meal>> by lazy {
        MutableLiveData<List<Meal>>().also {
            loadMeals()
        }
    }

    fun start(category: String) {
        _category.value = category
    }

    fun getTracks(): LiveData<List<Meal>> {
        return meals
    }

    fun loadMeals() {
        val getAlbums = ApiClient.build()?.getMeals(_category.value.toString())
        getAlbums?.enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful) {
                    meals.postValue(response.body()?.meals)
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.d("MealViewModel", t.toString())
            }

        })
    }
}