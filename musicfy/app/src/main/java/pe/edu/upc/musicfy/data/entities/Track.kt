package pe.edu.upc.musicfy.data.entities

import com.google.gson.annotations.SerializedName

class Track
    (
    @SerializedName("strAlbum")
    val album: String,

    @SerializedName("strArtist")
    val artist: String,

    @SerializedName("strTrack")
    val name: String
)