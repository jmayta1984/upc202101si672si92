package pe.edu.upc.materialdesing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.materialdesing.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        _binding = binding

        binding.loginButton.setOnClickListener {

            val user = binding.userInput.text.toString()
            val action = LoginFragmentDirections.actionLoginFragmentToProductGridFragment(user)

            NavHostFragment.findNavController(this)
                .navigate(action)

        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}