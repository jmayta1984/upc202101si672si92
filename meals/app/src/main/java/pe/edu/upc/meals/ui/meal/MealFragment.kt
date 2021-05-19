package pe.edu.upc.meals.ui.meal

import android.os.Bundle
import androidx.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.upc.meals.data.entities.Meal
import pe.edu.upc.meals.databinding.FragmentMealBinding

class MealFragment : Fragment() {

    private lateinit var adapter: MealAdapter
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!
    private val model: MealViewModel by viewModels()
    private val args: MealFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupView()
        setupRecyclerView()
        setupObservers()
        loadMeals()
        return binding.root
    }

    private fun setupView() {
        val category = args.categoryArg
        binding.tvCategory.text = category.name
        binding.ivCategory.transitionName = category.name
        binding.tvDescription.text = category.description
        Glide.with(this).load(category.thumb).into(binding.ivCategory)
    }

    private fun setupRecyclerView() {
        adapter = MealAdapter(binding.root.context)
        binding.rvMeal.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        binding.rvMeal.adapter = adapter
    }

    private fun setupObservers() {
        model.getTracks().observe(viewLifecycleOwner, { meals ->
            if (meals != null)
                adapter.setItems(meals as ArrayList<Meal>)
        })
    }

    private fun loadMeals() {
        model.start(args.categoryArg.name)
        model.loadMeals()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}