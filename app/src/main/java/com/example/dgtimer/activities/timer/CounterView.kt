package com.example.dgtimer.activities.timer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.dgtimer.R
import com.example.dgtimer.databinding.ViewCounterBinding
import com.example.dgtimer.utils.TimeUtils.MILLIS
import java.lang.Integer.min

class CounterView(
    context: Context
) : LinearLayout(context) {
    private val binding: ViewCounterBinding =
        ViewCounterBinding.inflate(LayoutInflater.from(context), this, true)

    private var onClick: ((Int) -> Unit)? = null

    fun updateCounter(counter: Counter) {
        with(binding) {
            val seconds =
                if (counter.currentTime < 100) {
                    0
                } else {
                    min(
                        (counter.currentTime / MILLIS).toInt() + 1,
                        (counter.totalTime / MILLIS).toInt()
                    )
                }
            tvCounterCount.text = seconds.toString()
            tvCounterCount.setTextColor(getTextColorByActiveState(counter.isActive))
            tvCounterName.setTextColor(getTextColorByActiveState(counter.isActive))
            val counterViewCount =
                (parent as ViewGroup).children.filter { it is CounterView }.count()
            tvCounterName.text =
                if (counter.index == 0 && counterViewCount > 1) {
                    context.getString(R.string.latte)
                } else {
                    context.getString(counter.typeStringResId)
                }
            root.setOnClickListener {
                onClick?.invoke(counter.index)
            }
        }
    }

    fun setOnClickListener(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }

    private fun getTextColorByActiveState(isActive: Boolean) =
        if (isActive) {
            ContextCompat.getColor(context, R.color.colorBackground)
        } else {
            ContextCompat.getColor(context, R.color.colorTextTransparent)
        }
}