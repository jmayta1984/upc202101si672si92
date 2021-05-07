package pe.edu.upc.wearappdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.wearappdemo.databinding.MenuItemBinding

class MenuAdapter(private val menuItems: ArrayList<MenuItem>, val context: Context) :
    RecyclerView.Adapter<MenuAdapter.Prototype>() {

    inner class Prototype(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindTo(menuItem: MenuItem) {

            val ivItem = itemView.findViewById<ImageView>(R.id.menu_icon).apply {
                setImageResource(menuItem.image)
            }
            val tvItem = itemView.findViewById<TextView>(R.id.menu_text).apply {


                text = menuItem.text
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Prototype {

        val binding = MenuItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return Prototype(binding.root)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: Prototype, position: Int) {
        holder.bindTo(menuItems[position])
    }
}