package pe.edu.upc.musicfy.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.musicfy.data.entities.Album
import pe.edu.upc.musicfy.data.entities.Track

class TrackResponse(
    @SerializedName("track")
    val tracks: List<Track>
)