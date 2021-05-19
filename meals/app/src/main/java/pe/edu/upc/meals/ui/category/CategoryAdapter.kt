package pe.edu.upc.meals.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.upc.meals.data.entities.Category
import pe.edu.upc.meals.databinding.PrototypeCategoryBinding

class CategoryAdapter(
    private val context: Context,
    private val listener: CategoryItemListener
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryPrototype>() {
    private val items: MutableList<Category> = ArrayList()

    interface CategoryItemListener {
        fun onClickedCategory(category: Category, imageView: ImageView)
    }

    fun setItems(items: ArrayList<Category>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPrototype {
        val binding =
            PrototypeCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryPrototype(binding)
    }

    override fun onBindViewHolder(prototype: CategoryPrototype, position: Int) {
        prototype.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CategoryPrototype(private val binding: PrototypeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var category: Category

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(category: Category) {
            this.category = category
            binding.tvName.text = this.category.name
            binding.ivImage.transitionName = this.category.name
            Glide.with(context).load(this.category.thumb).into(binding.ivImage)
        }

        override fun onClick(v: View?) {
            listener.onClickedCategory(category, binding.ivImage)
        }
    }
}