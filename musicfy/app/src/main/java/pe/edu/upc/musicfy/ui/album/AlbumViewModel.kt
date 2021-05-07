package pe.edu.upc.musicfy.ui.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.musicfy.data.entities.Album
import pe.edu.upc.musicfy.data.remote.ApiClient
import pe.edu.upc.musicfy.data.remote.AlbumResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel : ViewModel() {
    private val _artist = MutableLiveData<String>()
    private val albums: MutableLiveData<List<Album>> by lazy {
        MutableLiveData<List<Album>>().also {
            searchAlbums()
        }
    }

    fun start(artist: String) {
        _artist.value = artist
    }

    fun getAlbums(): LiveData<List<Album>> {
        return albums
    }

    fun searchAlbums() {
        val getAlbums = ApiClient.build()?.getAlbums(_artist.value.toString())
        getAlbums?.enqueue(object : Callback<AlbumResponse> {
            override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
                if (response.isSuccessful) {
                    albums.postValue(response.body()?.albums)
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                Log.d("AlbumViewModel", t.toString())
            }
        })
    }
}