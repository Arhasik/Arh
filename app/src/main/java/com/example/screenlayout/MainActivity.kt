package com.example.screenlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.screenlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.custom.setText("Top list", "Bottom list")
    }
}