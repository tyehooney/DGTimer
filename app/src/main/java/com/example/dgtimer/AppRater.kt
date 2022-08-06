package com.example.dgtimer

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import com.example.dgtimer.utils.TimeUtils.MILLIS
import javax.inject.Inject

class AppRater @Inject constructor(
    private val preferences: DGTimerPreferences
) {
    fun set(activity: Activity) {
        if (preferences.getBoolean(PrefKey.IsRaterShown())) {
            activity.finish()
            return
        }

        val launchCount =
            preferences.getInt(PrefKey.RaterLaunchCount(), 0) + 1
        preferences.put(PrefKey.RaterLaunchCount(), launchCount)

        val current = System.currentTimeMillis()
        var firstLaunchedDate =
            preferences.getLong(PrefKey.FirstLaunchedDate(), 0L)
        if (firstLaunchedDate == 0L) {
            firstLaunchedDate = current
            preferences.put(PrefKey.FirstLaunchedDate(), firstLaunchedDate)
        }

        if (
            launchCount >= LAUNCHES_UNTIL_PROMPT &&
            current >= firstLaunchedDate + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * MILLIS)
        ) {
            showRateDialog(activity)
        } else {
            activity.finish()
        }
    }

    private fun showRateDialog(activity: Activity) {
        AlertDialog.Builder(activity)
            .setTitle(R.string.ratingDialogTitle)
            .setMessage(R.string.induceRate)
            .setNegativeButton(R.string.rate) { _, _ ->
                launchGooglePlayForRating(activity)
                preferences.put(PrefKey.IsRaterShown(), true)
            }.setPositiveButton(R.string.later) { _, _ ->
                preferences.put(PrefKey.FirstLaunchedDate(), System.currentTimeMillis())
            }.create().apply {
                setOnDismissListener {
                    activity.finish()
                }
            }.show()
    }

    companion object {
        private const val DAYS_UNTIL_PROMPT = 3
        private const val LAUNCHES_UNTIL_PROMPT = 3
        private const val GOOGLE_PLAY_LINK =
            "http://play.google.com/store/apps/details?id=com.tyehooney.dgtimer"

        fun launchGooglePlayForRating(activity: Activity) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(GOOGLE_PLAY_LINK)
                )
            )
        }
    }
}