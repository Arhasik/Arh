package com.example.screenlayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.screenlayout.databinding.ActivityMainBinding.inflate
import com.example.screenlayout.databinding.MyCustomViewBinding

class CustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout (context, attrs, defStyleAttr){
    val binding = MyCustomViewBinding.inflate(LayoutInflater.from(context))
    init {
        addView(binding.root)
    }
    fun setText (textFirst: String, textSecond: String){
        binding.textFirst.text = textFirst
        binding.textSecond.text = textSecond
    }
}
