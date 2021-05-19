package pe.edu.upc.meals.data.entities

import com.google.gson.annotations.SerializedName

class Meal(
    @SerializedName("idMeal")
    val id: String,

    @SerializedName("strMeal")
    val name: String,

    @SerializedName("strMealThumb")
    val thumb: String,
)