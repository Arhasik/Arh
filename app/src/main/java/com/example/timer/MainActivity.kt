package com.example.timer;

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var currentProcess = 5
    private lateinit var seekBar: SeekBar
    private lateinit var processBar: ProgressBar
    private lateinit var processText: TextView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar)
        processBar = findViewById(R.id.progress_circular)
        processText = findViewById(R.id.textTimer)
        button = findViewById(R.id.button)

        var timer: Job?=null
        var coroutineFirst= CoroutineScope(Dispatchers.Main)
        var timerStop: Job?=null
        var coroutineSecond= CoroutineScope(Dispatchers.Main)
        var timerActive = false
        setDefault()

        button.setOnClickListener {
            timerStop = coroutineSecond.launch {
                if (!timerActive){
                    timerActive= true
                    timer = coroutineFirst.launch {
                        timerStart()                    }
                }
                else {
                    timer?.cancel()
                    timerActive = false
                    Toast.makeText(applicationContext, "Timer finished", Toast.LENGTH_LONG.show())
                    setDefault()
                    seekBar.isEnabled = true
                }
                if (currentProcess == 0){
                    timerActive = false
                    timer?.cancel()
                    Toast.makeText(applicationContext, "Timer finished", Toast.LENGTH_LONG.show())
                    setDefault()
                    seekBar.isEnabled = true
                }
            }

        }

    }
    suspend fun timerStart(){
        seekBar.isEnabled = false
        button.text = "Start"
        while (currentProcess>0){
            currentProcess--
            processText.text=currentProcess.toString()
            processBar.progress=currentProcess
            delay(1000)
        }
    }
    fun setDefault(){
        processText.text=currentProcess.toString()
        processBar.max=currentProcess
        processBar.progress = currentProcess
        button.text = "Stop"
    }
}

