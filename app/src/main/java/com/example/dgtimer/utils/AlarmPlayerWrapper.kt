package com.example.dgtimer.utils

import android.content.Context
import android.content.Context.VIBRATOR_MANAGER_SERVICE
import android.content.Context.VIBRATOR_SERVICE
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class AlarmPlayerWrapper(
    private val context: Context
) {
    private var alarmPlayer: MediaPlayer? = null
    private val vibrator: Vibrator by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager)
                .defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
    }
    private val vibratePattern = longArrayOf(0, 500, 500, 500)

    fun ringAlarm(alarmId: Int, volume: Float) {
        if (alarmPlayer?.isPlaying == true) {
            release()
        }
        alarmPlayer = MediaPlayer.create(
            context,
            context.resources.getIdentifier(
                "alarm_$alarmId",
                "raw",
                context.packageName
            )
        ).apply {
            setVolume(volume, volume)
            start()
        }
    }

    fun vibrate(amplitude: Int) {
        vibrator.cancel()
        vibrator.vibrate(
            VibrationEffect.createWaveform(
                vibratePattern,
                intArrayOf(0, amplitude, 0, amplitude),
                -1
            )
        )
    }

    fun release() {
        alarmPlayer?.stop()
        alarmPlayer?.release()
        alarmPlayer = null
    }
}