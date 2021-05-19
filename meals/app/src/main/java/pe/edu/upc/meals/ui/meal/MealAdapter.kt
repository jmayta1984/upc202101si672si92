package pe.edu.upc.meals.ui.meal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.upc.meals.data.entities.Meal
import pe.edu.upc.meals.databinding.PrototypeMealBinding

class MealAdapter(private val context: Context) :
    RecyclerView.Adapter<MealAdapter.MealPrototype>() {
    private val items: MutableList<Meal> = ArrayList()
    fun setItems(items: ArrayList<Meal>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPrototype {
        val binding =
            PrototypeMealBinding.inflate(LayoutInflater.from(context), parent, false)
        return MealPrototype(binding)
    }

    override fun onBindViewHolder(prototype: MealPrototype, position: Int) {
        prototype.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MealPrototype(private val binding: PrototypeMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var meal: Meal
        fun bind(meal: Meal) {
            this.meal = meal
            binding.tvName.text = this.meal.name
            Glide.with(context).load(this.meal.thumb).into(binding.ivImage)
        }
    }
}