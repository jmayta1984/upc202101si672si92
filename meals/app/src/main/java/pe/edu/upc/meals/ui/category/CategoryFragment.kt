package pe.edu.upc.meals.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.meals.data.entities.Category
import pe.edu.upc.meals.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment(), CategoryAdapter.CategoryItemListener {

    private lateinit var adapter: CategoryAdapter
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val model: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupObservers()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = CategoryAdapter(binding.root.context, this)
        binding.rvCategory.layoutManager =
            GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
        binding.rvCategory.adapter = adapter
    }

    private fun setupObservers() {
        model.getCategories().observe(viewLifecycleOwner, { categories ->
            if (categories != null)
                adapter.setItems(categories as ArrayList<Category>)
        }
        )
    }


    override fun onClickedCategory(category: Category, imageView: ImageView) {
        val action = CategoryFragmentDirections.actionCategoryToMeal(category)
        val extras = FragmentNavigatorExtras(
            imageView to category.name
        )
        NavHostFragment.findNavController(this)
            .navigate(action, extras)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}