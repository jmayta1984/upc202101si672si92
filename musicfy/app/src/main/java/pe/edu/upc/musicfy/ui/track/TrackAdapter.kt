package pe.edu.upc.musicfy.ui.track

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.musicfy.data.entities.Track
import pe.edu.upc.musicfy.databinding.PrototypeTrackBinding

class TrackAdapter(private val context: Context) :
    RecyclerView.Adapter<TrackAdapter.TrackPrototype>() {
    private val items: MutableList<Track> = ArrayList()

    fun setItems(items: ArrayList<Track>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackPrototype {
        val binding =
            PrototypeTrackBinding.inflate(LayoutInflater.from(context), parent, false)
        return TrackPrototype(binding)
    }

    override fun onBindViewHolder(prototype: TrackPrototype, position: Int) {
        prototype.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TrackPrototype(private val binding: PrototypeTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var track: Track
        fun bind(track: Track) {
            this.track = track
            binding.tvName.text = this.track.name
            binding.tvArtist.text = this.track.artist
        }

    }
}