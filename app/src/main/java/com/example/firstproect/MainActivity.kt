package com.example.firstproect

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.firstproect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var maxPessenger = 50
    var pessenger = 0
    val textFirst = "All places are free"
    val textSecond = "Places left"
    val textThree = "Too many passengers"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun counterPlace() {
            pessenger.toString()
            maxPessenger.toString()
            binding.buttonPanel.visibility = View.INVISIBLE
            binding.anoutherTextView.text = "$pessenger"
            binding.textView.setTextColor(Color.rgb(12, 189, 47))
            binding.textView.text = "$textFirst : $maxPessenger"
            binding.plusOneButton.isEnabled = true
            binding.minusOneButton.isEnabled = false
            if (pessenger in 1..maxPessenger) {
                binding.textView.setTextColor(Color.rgb(35, 12, 189))
                binding.textView.text = "$textSecond: ${maxPessenger - pessenger}"
                binding.minusOneButton.isEnabled = true
            }
            if (pessenger == maxPessenger) {
                binding.textView.setTextColor(Color.rgb(200, 0, 0))
                binding.plusOneButton.isEnabled = false
                binding.textView.text = "$textThree: $pessenger"
                binding.buttonPanel.visibility = View.VISIBLE
                binding.minusOneButton.isEnabled = true
            }
        }
        counterPlace()
        binding.plusOneButton.setOnClickListener {
            pessenger++
            counterPlace()
        }

        binding.minusOneButton.setOnClickListener {
            pessenger--
            counterPlace()
        }

        binding.buttonPanel.setOnClickListener {
            pessenger = 0
            counterPlace()
        }
    }
}