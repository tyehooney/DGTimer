package com.example.dgtimer.activities.settings

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.os.VibrationEffect
import android.os.VibratorManager
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dgtimer.DGTimerPreferences.Companion.vibratePattern
import com.example.dgtimer.R
import com.example.dgtimer.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: KSettingsViewModel by viewModels()

    private val vibrator by lazy {
        (getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
    }
    private val strAlarms by lazy {
        resources.getStringArray(R.array.strAlarms)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
    }

    private fun initView() {
        with(binding) {
            sbVolume.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        value: Int,
                        fromUser: Boolean) {
                        viewModel.setVolume(value)
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        ringAlarm(viewModel.alarm.value)
                    }
                }
            )

            sbAmplitude.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        value: Int,
                        fromUser: Boolean) {
                        viewModel.setAmplitude(value)
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        vibrate()
                    }
                }
            )

            llBtnChooseAlarm.setOnClickListener {
                showChooseAlarmDialog()
            }

            tvAppVersion.text = packageManager
                .getPackageInfo(packageName, 0)
                .versionName

            tvBtnReset.setOnClickListener {
                viewModel.resetAllSettings()
            }

            tvBtnSave.setOnClickListener {
                saveAndFinish()
            }

            tvBtnGoReview.setOnClickListener {
                goReview()
            }
        }
    }

    private fun ringAlarm(alarm: Int) {
        val volume = viewModel.volume.value / 100f
        MediaPlayer.create(
            this,
            resources.getIdentifier("alarm_$alarm","raw", packageName)
        ).apply {
            setVolume(volume, volume)
            start()
        }
    }

    private fun vibrate() {
        val amplitude = viewModel.amplitude.value
        vibrator.vibrate(
            VibrationEffect.createWaveform(
                vibratePattern, intArrayOf(0, amplitude, 0, amplitude), -1
            )
        )
    }

    private fun showChooseAlarmDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.selectAlarm)
            .setSingleChoiceItems(strAlarms, viewModel.alarm.value) { _, which ->
                ringAlarm(which)
            }.setPositiveButton(R.string.save) { dialog, which ->
                viewModel.setAlarm(which)
                dialog.dismiss()
            }.setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun goReview() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(GOOGLE_PLAY_LINK)
        }
        startActivity(intent)
    }

    private fun saveAndFinish() {
        viewModel.saveSettings()
        finish()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.volume.collectLatest {
                        binding.tvVolume.text = it.toString()
                    }
                }

                launch {
                    viewModel.amplitude.collectLatest {
                        binding.tvAmplitude.text = it.toString()
                    }
                }

                launch {
                    viewModel.alarm.collect {
                        binding.tvAlarm.text = strAlarms[it]
                    }
                }
            }
        }
    }

    companion object {
        private const val GOOGLE_PLAY_LINK =
            "http://play.google.com/store/apps/details?id=com.tyehooney.dgtimer"

        fun createSettingsActivityIntent(
            callerActivity: Activity
        ): Intent = Intent(callerActivity, KSettingsActivity::class.java)
    }
}