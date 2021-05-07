package pe.edu.upc.musicfy.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.musicfy.data.entities.Album

class AlbumResponse(
    @SerializedName("album")
    val albums: List<Album>
)