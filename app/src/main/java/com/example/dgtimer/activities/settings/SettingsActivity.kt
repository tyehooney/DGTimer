package com.example.dgtimer.activities.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dgtimer.AppRater.Companion.launchGooglePlayForRating
import com.example.dgtimer.R
import com.example.dgtimer.databinding.ActivitySettingsBinding
import com.example.dgtimer.utils.AlarmPlayerWrapper
import com.example.dgtimer.utils.Extensions.getPackageInfoCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModels()

    private val alarmPlayer by lazy {
        AlarmPlayerWrapper(this)
    }
    private val strAlarms by lazy {
        resources.getStringArray(R.array.strAlarms)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
    }

    override fun onDestroy() {
        alarmPlayer.release()
        super.onDestroy()
    }

    private fun initView() {
        with(binding) {
            sbVolume.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        value: Int,
                        fromUser: Boolean
                    ) {
                        viewModel.setVolume(value)
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        ringAlarm(viewModel.alarm.value)
                    }
                }
            )
            sbVolume.progress = viewModel.volume.value

            sbAmplitude.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        value: Int,
                        fromUser: Boolean
                    ) {
                        viewModel.setAmplitude(value)
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        vibrate()
                    }
                }
            )
            sbAmplitude.progress = viewModel.amplitude.value

            llBtnChooseAlarm.setOnClickListener {
                showChooseAlarmDialog()
            }

            tvAppVersion.text = packageManager
                .getPackageInfoCompat(packageName)
                .versionName

            tvBtnReset.setOnClickListener {
                viewModel.resetAllSettings()
            }

            tvBtnSave.setOnClickListener {
                saveAndFinish()
            }

            tvBtnGoReview.setOnClickListener {
                launchGooglePlayForRating(this@SettingsActivity)
            }
        }
    }

    private fun ringAlarm(alarm: Int) {
        val volume = viewModel.volume.value / 100f
        alarmPlayer.ringAlarm(alarm, volume)
    }

    private fun vibrate() {
        val amplitude = viewModel.amplitude.value
        alarmPlayer.vibrate(amplitude)
    }

    private fun showChooseAlarmDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.selectAlarm)
            .setSingleChoiceItems(strAlarms, viewModel.alarm.value) { _, which ->
                viewModel.selectedAlarmIndex = which
                ringAlarm(which)
            }.setPositiveButton(R.string.save) { dialog, _ ->
                viewModel.setAlarm(viewModel.selectedAlarmIndex)
                dialog.dismiss()
            }.setNegativeButton(R.string.cancel, null)
            .show()
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
                        if (binding.sbVolume.progress != it) {
                            binding.sbVolume.progress = it
                        }
                    }
                }

                launch {
                    viewModel.amplitude.collectLatest {
                        binding.tvAmplitude.text = it.toString()
                        if (binding.sbAmplitude.progress != it) {
                            binding.sbAmplitude.progress = it
                        }
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
        fun createSettingsActivityIntent(
            callerActivity: Activity
        ): Intent = Intent(callerActivity, SettingsActivity::class.java)
    }
}