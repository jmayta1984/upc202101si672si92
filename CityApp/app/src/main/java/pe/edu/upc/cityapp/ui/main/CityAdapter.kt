package pe.edu.upc.cityapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.cityapp.data.entities.City
import pe.edu.upc.cityapp.databinding.PrototypeCityBinding

class CityAdapter(private val context: Context) :
    RecyclerView.Adapter<CityAdapter.CityPrototype>() {
    private val items: MutableList<City> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityPrototype {
        val binding = PrototypeCityBinding.inflate(LayoutInflater.from(context), parent, false)
        return CityPrototype(binding)
    }

    override fun onBindViewHolder(prototype: CityPrototype, position: Int) {
        prototype.bind(items[position])
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    fun setItems(items: ArrayList<City>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class CityPrototype(private val binding: PrototypeCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var city: City

        fun bind(city: City) {
            this.city = city
            binding.tvCity.text = this.city.name
            binding.tvCountry.text =
                this.city.country + " latitude: ${this.city.latitude.toString()}"
        }
    }

}