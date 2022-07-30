package com.example.dgtimer.utils

object TimeUtils {
    const val MILLIS = 1000L

    fun stageToSecond(stage: Int): Int =
        when (stage) {
            1 -> 8
            2 -> 10
            3 -> 15
            4 -> 21
            5 -> 24
            6 -> 28
            7 -> 38
            8 -> 60
            else -> 0
        }
}