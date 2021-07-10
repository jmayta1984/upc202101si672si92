package pe.edu.upc.cityapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.cityapp.data.entities.City
import pe.edu.upc.cityapp.databinding.PrototypeCityBinding

class CityAdapter(
    private val context: Context,
    private val listener: CityItemListener,
) :
    RecyclerView.Adapter<CityAdapter.CityPrototype>() {
    private val items: MutableList<City> = ArrayList()

    interface CityItemListener {
        fun onClickedCity(city: City)
        fun onLongClickedCity(city: City)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityPrototype {
        val binding = PrototypeCityBinding.inflate(LayoutInflater.from(context), parent, false)
        return CityPrototype(binding, listener)
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

    inner class CityPrototype(
        private val binding: PrototypeCityBinding,
        private val listener: CityItemListener,

    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {
        private lateinit var city: City

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        fun bind(city: City) {
            this.city = city
            binding.tvCity.text = this.city.name
            binding.tvCountry.text = this.city.country
        }

        override fun onClick(v: View?) {
            listener.onClickedCity(city)
        }

        override fun onLongClick(v: View?): Boolean {
            listener.onLongClickedCity(city)
            return true
        }
    }

}