package com.example.dgtimer.activities.timer

data class Counter(
    val type: String,
    val totalTime: Long,
    val index: Int,
    val isActive: Boolean,
    val currentTime: Long = totalTime,
)
