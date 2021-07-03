package pe.edu.upc.cityapp.data.entities

import com.google.gson.annotations.SerializedName

class City(
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("country")
    val country: String
)