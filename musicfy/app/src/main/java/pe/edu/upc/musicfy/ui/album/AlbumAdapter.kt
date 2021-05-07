package pe.edu.upc.musicfy.ui.album

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.upc.musicfy.data.entities.Album
import pe.edu.upc.musicfy.databinding.PrototypeAlbumBinding

class AlbumAdapter(
    val context: Context,
    private val listener: AlbumItemListener
) :
    RecyclerView.Adapter<AlbumAdapter.AlbumPrototype>() {
    private val items: MutableList<Album> = ArrayList()

    interface AlbumItemListener {
        fun onClickedAlbum(album: Album, imageView: ImageView)
    }

    fun setItems(items: ArrayList<Album>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumPrototype {
        val binding =
            PrototypeAlbumBinding.inflate(LayoutInflater.from(context), parent, false)

        return AlbumPrototype(binding, listener)
    }

    override fun onBindViewHolder(prototype: AlbumPrototype, position: Int) {
        prototype.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class AlbumPrototype(
        private val binding: PrototypeAlbumBinding,
        private val listener: AlbumItemListener
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var album: Album

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(album: Album) {
            this.album = album
            binding.tvName.text = this.album.name
            binding.ivImage.transitionName = this.album.id
            Glide.with(context).load(this.album.thumb).into(binding.ivImage)
        }

        override fun onClick(v: View?) {
            listener.onClickedAlbum(album, binding.ivImage)
        }
    }
}