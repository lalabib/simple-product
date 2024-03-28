package com.lalabib.latihan.simpleproduct.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.utils.SharedObject.disableView
import com.lalabib.latihan.simpleproduct.utils.SharedObject.enableView

class StepCounter(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var currentCount = 1
    private var decreaseBtn: ImageView
    private var increaseBtn: ImageView
    private var count: TextView
    private lateinit var countChangeListener: (count: Int) -> Unit

    init {
        inflate(context, R.layout.item_counter, this)
        decreaseBtn = findViewById(R.id.decrease)
        increaseBtn = findViewById(R.id.increase)
        count = findViewById(R.id.count)
        decreaseBtn.disableView()
        setupListeners()
    }

    fun registerCountListener(listener: (count: Int) -> Unit) {
        countChangeListener = listener
    }

    private fun setupListeners() {
        increaseBtn.setOnClickListener {
            currentCount = count.text.toString().toInt()
            currentCount++
            if (currentCount > 1)
                decreaseBtn.enableView()
            count.text = currentCount.toString()
            countChangeListener(currentCount)
        }

        decreaseBtn.setOnClickListener {
            currentCount = count.text.toString().toInt()
            currentCount--
            if (currentCount <= 1)
                decreaseBtn.disableView()
            count.text = currentCount.toString()
            countChangeListener(currentCount)
        }
    }
}