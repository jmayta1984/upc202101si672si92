package pe.edu.upc.musicfy.ui.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import pe.edu.upc.musicfy.data.entities.Track
import pe.edu.upc.musicfy.databinding.FragmentTrackBinding

class TrackFragment : Fragment() {

    private lateinit var adapter: TrackAdapter
    private var _binding: FragmentTrackBinding? = null
    private val binding get() = _binding!!
    private val model: TrackViewModel by viewModels()
    private val args: TrackFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupView()
        setupRecyclerView()
        setupObservers()
        loadTracks()
        return binding.root
    }

    private fun setupView() {
        val album = args.albumArg
        binding.tvAlbum.text = album.name
        binding.ivImage.transitionName = album.id
        Glide.with(this).load(album.thumb).into(binding.ivImage)
    }

    private fun setupRecyclerView() {
        adapter = TrackAdapter(binding.root.context)
        binding.rvTrack.layoutManager =
            LinearLayoutManager(binding.root.context)
        binding.rvTrack.adapter = adapter
    }

    private fun setupObservers() {
        model.getTracks().observe(viewLifecycleOwner, { tracks ->
            if (tracks != null)
                adapter.setItems(tracks as ArrayList<Track>)
            else adapter.setItems(ArrayList())
        })
    }

    private fun loadTracks() {
        model.start(args.albumArg.id)
        model.loadTracks()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
