package com.tyehooney.ui.activities.timer

import android.content.Context
import android.widget.LinearLayout

class CounterView(
    context: Context
) : LinearLayout(context) {

    fun updateCounter(counter: Counter) {
        // Simple minimal implementation
        println("CounterView updated with counter: ${counter.index}")
    }

    fun setOnClickListener(onClick: (Int) -> Unit) {
        // Simple minimal implementation
        println("CounterView click listener set")
    }
}