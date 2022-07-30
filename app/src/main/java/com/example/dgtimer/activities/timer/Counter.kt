package com.example.dgtimer.activities.timer

data class Counter(
    val type: String,
    val totalTime: Int,
    val index: Int,
    val isActive: Boolean,
    val currentTime: Int = totalTime,
)
