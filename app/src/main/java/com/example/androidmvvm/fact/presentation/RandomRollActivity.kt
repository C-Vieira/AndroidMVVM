package com.example.androidmvvm.fact.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidmvvm.R
import com.example.androidmvvm.databinding.RandomRollViewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomRollActivity : AppCompatActivity() {
    //private lateinit var binding: RandomRollViewBinding
    private val randomRollViewModel: RandomRollViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        //binding = RandomRollViewBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        setContentView(R.layout.random_roll_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val label = findViewById<TextView>(R.id.label)
        val button = findViewById<Button>(R.id.button)

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                randomRollViewModel.state.collect { newFact ->
                    label.text = newFact
                }
            }
        }

        button.setOnClickListener {
            randomRollViewModel.roll()
        }
    }
}