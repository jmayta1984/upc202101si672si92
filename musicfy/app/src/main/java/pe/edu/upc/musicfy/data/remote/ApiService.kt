package pe.edu.upc.musicfy.data.remote

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("searchalbum.php")
    fun getAlbums(@Query("s") artist: String): Call<AlbumResponse>


    @GET("track.php")
    fun getTracks(@Query("m") albumId: String): Call<TrackResponse>
}