package pe.edu.upc.musicfy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.upc.musicfy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}