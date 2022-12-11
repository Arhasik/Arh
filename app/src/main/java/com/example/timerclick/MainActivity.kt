package com.example.timerclick;

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.timerclick.R
import com.google.android.material.slider.Slider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var currentProcess = 10
    private lateinit var sliderBar: Slider
    private lateinit var progressCircular: ProgressBar
    private lateinit var textTimer: TextView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderBar = findViewById(R.id.slider)
        progressCircular = findViewById(R.id.progress_circular)
        textTimer = findViewById(R.id.textTimer)
        button = findViewById(R.id.button)


        sliderBar.addOnChangeListener { slider, value, fromUser ->
            currentProcess = sliderBar.value.toInt()
            textTimer.text = value.toInt().toString()
            progressCircular.max = currentProcess
            progressCircular.progress = currentProcess
        }

        var isTimerActive = false
setDefault()
        button.setOnClickListener {
            val startTimer = CoroutineScope(Job() + Dispatchers.Main)
            startTimer.launch {
                if (!isTimerActive) {
                    sliderBar.isEnabled = false
                    isTimerActive = true
                    button.text = "Stop"
                    for (i in 0..60) {
                        if (currentProcess <= 0) startTimer.cancel()
                        textTimer.text = progressCircular.toString()
                        progressCircular.progress = currentProcess
                        currentProcess--
                        delay(1000)
                    }
                    sliderBar.isEnabled = true
                    sliderBar.value = 0f
                    isTimerActive = false
                    button.text = "Start"
                } else {
                    button.text = "Start"
                    isTimerActive = false
                    sliderBar.isEnabled = true
                    sliderBar.value = 0f
                }
            }
        }
    }
    fun setDefault(){
        textTimer.text=currentProcess.toString()
        sliderBar.value = currentProcess.toFloat()
        progressCircular.progress = currentProcess
        button.text = "Start"
    }
}

