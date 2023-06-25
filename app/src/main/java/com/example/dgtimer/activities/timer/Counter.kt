package com.example.dgtimer.activities.timer

data class Counter(
    val typeStringResId: Int,
    val totalTime: Long,
    val index: Int,
    val isActive: Boolean,
    val currentTime: Long = totalTime,
)
