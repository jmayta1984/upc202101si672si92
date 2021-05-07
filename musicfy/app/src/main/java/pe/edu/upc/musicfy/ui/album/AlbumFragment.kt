package pe.edu.upc.musicfy.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.musicfy.data.entities.Album
import pe.edu.upc.musicfy.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment(), AlbumAdapter.AlbumItemListener {
    private lateinit var adapter: AlbumAdapter
    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private val model: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = AlbumAdapter(binding.root.context, this)
        binding.rvAlbum.layoutManager =
            GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
        binding.rvAlbum.adapter = adapter
    }

    private fun setupListeners() {
        binding.btSearch.setOnClickListener {
            searchAlbum()
        }
    }

    private fun setupObservers() {
        model.getAlbums().observe(viewLifecycleOwner, { albums ->
            if (albums != null)
                adapter.setItems(albums as ArrayList<Album>)
            else adapter.setItems(ArrayList())
        })
    }

    private fun searchAlbum() {
        val artist = binding.etArtist.text.toString()
        model.start(artist)
        model.searchAlbums()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onClickedAlbum(album: Album, imageView: ImageView) {
        val action = AlbumFragmentDirections.actionAlbumToTrack(album)
        val extras = FragmentNavigatorExtras(
            imageView to album.id
        )
        NavHostFragment.findNavController(this)
            .navigate(action, extras)
    }

}