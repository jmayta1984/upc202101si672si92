package pe.edu.upc.cityapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.upc.cityapp.data.entities.City
import pe.edu.upc.cityapp.databinding.ActivityMainBinding
import pe.edu.upc.cityapp.ui.city.CityActivity

class MainActivity : AppCompatActivity(), CityAdapter.CityItemListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CityAdapter
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupListeners()
        setupObservers()

    }

    private fun setupListeners() {
        binding.btSearch.setOnClickListener {
            val name = binding.etCity.text.toString()
            val limit = "10"
            val apiKey = "77d03b47f1fe23f86cf01f3a32c94563"
            model.start(name, apiKey, limit)
            model.searchCities()
        }
    }

    private fun setupRecyclerView() {
        adapter = CityAdapter(binding.root.context, this)
        binding.rvCity.layoutManager = LinearLayoutManager(this)
        binding.rvCity.adapter = adapter
    }

    private fun setupObservers() {
        model.getCities().observe(this, { cities ->
            if (cities != null)
                adapter.setItems(cities as ArrayList<City>)
        })
    }

    override fun onClickedCity(city: City) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra("extraCity", city.name)
        intent.putExtra("extraLat", city.latitude)
        intent.putExtra("extraLon", city.longitude)
        startActivity(intent)
    }

    override fun onLongClickedCity(city: City) {
        /*
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, city.name)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent,"Sharing")
        startActivity(shareIntent)
        */

         ShareCompat.IntentBuilder
             .from(this)
             .setType("text/plain")
             .setChooserTitle("Sharing")
             .setText(city.name)
             .startChooser()
    }
}