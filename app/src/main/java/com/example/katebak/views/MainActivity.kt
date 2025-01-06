package com.example.katebak.views

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.katebak.databinding.ActivityMainBinding
import com.example.katebak.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val config = Configuration(resources.configuration)
        config.setLayoutDirection(Locale("fa")) // Replace "fa" with your desired RTL language code
        resources.updateConfiguration(config, resources.displayMetrics)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}