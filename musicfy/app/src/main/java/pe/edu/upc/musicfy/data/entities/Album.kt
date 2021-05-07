package pe.edu.upc.musicfy.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(

    @SerializedName("idAlbum")
    val id: String,

    @SerializedName("strAlbum")
    val name: String,

    @SerializedName("strAlbumThumb")
    val thumb: String,

    @SerializedName("strArtist")
    val artist: String
) : Parcelable