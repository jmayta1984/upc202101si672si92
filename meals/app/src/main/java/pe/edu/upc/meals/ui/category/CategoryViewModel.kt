package pe.edu.upc.meals.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.meals.data.entities.Category
import pe.edu.upc.meals.data.remote.ApiClient
import pe.edu.upc.meals.data.remote.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel:ViewModel() {

    private val categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also {
            loadCategories()
        }
    }

    fun getCategories(): LiveData<List<Category>> {
        return categories
    }

    private fun loadCategories() {
        val getCategories = ApiClient.build()?.getCategories()

        getCategories?.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if (response.isSuccessful) {
                    categories.postValue(response.body()?.categories)
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
            }
        })

    }
}