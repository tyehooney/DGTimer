package com.example.dgtimer.utils

object TimeUtils {
    const val MILLIS = 1000L

    fun stageToMilliseconds(stage: Int): Long =
        when (stage) {
            1 -> 8 * MILLIS
            2 -> 10 * MILLIS
            3 -> 15 * MILLIS
            4 -> 21 * MILLIS
            5 -> 24 * MILLIS
            6 -> 28 * MILLIS
            7 -> 38 * MILLIS
            8 -> 60 * MILLIS
            else -> 0L
        }
}