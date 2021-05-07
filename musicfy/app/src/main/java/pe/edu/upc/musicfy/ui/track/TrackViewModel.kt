package pe.edu.upc.musicfy.ui.track

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.upc.musicfy.data.entities.Track
import pe.edu.upc.musicfy.data.remote.ApiClient
import pe.edu.upc.musicfy.data.remote.TrackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackViewModel : ViewModel() {
    private val _id = MutableLiveData<String>()
    private val tracks: MutableLiveData<List<Track>> by lazy {
        MutableLiveData<List<Track>>().also {
            loadTracks()
        }
    }

    fun start(id: String) {
        _id.value = id
    }

    fun getTracks(): LiveData<List<Track>> {
        return tracks
    }

    fun loadTracks() {
        val getAlbums = ApiClient.build()?.getTracks(_id.value.toString())
        getAlbums?.enqueue(object : Callback<TrackResponse> {
            override fun onResponse(call: Call<TrackResponse>, response: Response<TrackResponse>) {
                if (response.isSuccessful) {
                    tracks.postValue(response.body()?.tracks)
                }
            }

            override fun onFailure(call: Call<TrackResponse>, t: Throwable) {
                Log.d("TrackViewModel", t.toString())
            }

        })
    }
}