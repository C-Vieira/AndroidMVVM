package com.example.androidmvvm

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
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    private val viewModel: RandomRollViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appModule = module {
            single<RandomFactDataSource> { RandomFactDataSource() }
            single<FactRepository> { FactRepositoryImpl(get()) }
            viewModel { RandomRollViewModel(get()) }
        }
        startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        val label = findViewById<TextView>(R.id.label)
        val button = findViewById<Button>(R.id.button)

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect { newFact ->
                    label.text = newFact
                }
            }
        }

        button.setOnClickListener {
            viewModel.roll()
        }
    }
}