package com.example.dgtimer.activities.timer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dgtimer.R
import com.example.dgtimer.activities.settings.SettingsActivity
import com.example.dgtimer.databinding.ActivityTimerBinding
import com.example.dgtimer.setAd
import com.example.dgtimer.utils.AlarmPlayerWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private val viewModel: TimerViewModel by viewModels()

    private val alarmPlayer by lazy {
        AlarmPlayerWrapper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCapsuleData()
        initView()
        initObservers()
    }

    override fun onStop() {
        viewModel.resetCountDownTimer()
        super.onStop()
    }

    override fun onDestroy() {
        alarmPlayer.release()
        super.onDestroy()
    }

    private fun setCapsuleData() {
        val capsuleId = intent.getIntExtra(KEY_CAPSULE_ID, -1)
        viewModel.setCapsuleData(capsuleId)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        with(binding) {
            lifecycleScope.launch {
                viewModel.setCapsuleDataJob?.join()
                val capsule = viewModel.capsule ?: return@launch
                root.setBackgroundColor(capsule.colorAsInt)
                tvCapsuleName.text = capsule.name
                tvCapsuleTips.text = getString(R.string.tip1) +
                        if (capsule.stage.size > 1) "\n${getString(R.string.tip2)}"
                        else ""
            }
            ivBtnAlarmOption.setOnClickListener {
                viewModel.updateAlarmOn(viewModel.isAlarmOn.value.not())
            }
            ivBtnPlay.setOnClickListener {
                handlePlayButtonClickEvent()
            }
            ivBtnReplay.setOnClickListener {
                viewModel.resetCountDownTimer()
            }
            ivBtnGoSettings.setOnClickListener {
                startActivity(
                    SettingsActivity.createSettingsActivityIntent(this@TimerActivity)
                )
            }

            setAd(adView, this@TimerActivity, lifecycle)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.counters.collect {
                        updateCounterViews(it)
                    }
                }

                launch {
                    viewModel.counterState.collect {
                        updateCounterState(it)
                    }
                }

                launch {
                    viewModel.isAlarmOn.collect {
                        updateAlarmState(it)
                    }
                }
            }
        }
    }

    private fun handlePlayButtonClickEvent() {
        when (viewModel.counterState.value) {
            CounterState.Counting -> {
                viewModel.pauseCountDownTimer()
            }
            else -> {
                viewModel.playCountDownTimer()
            }
        }
    }

    private fun updateCounterState(counterState: CounterState) {
        binding.ivBtnPlay.setImageDrawable(
            when (counterState) {
                CounterState.Counting -> {
                    ContextCompat.getDrawable(this, R.drawable.ic_pause)
                }
                else -> {
                    ContextCompat.getDrawable(this, R.drawable.ic_play)
                }
            }
        )

        if (counterState == CounterState.Finished) {
            if (viewModel.isAlarmOn.value) {
                alarmPlayer.ringAlarm(viewModel.alarm, viewModel.alarmVolume)
            } else {
                alarmPlayer.vibrate(viewModel.alarmAmplitude)
            }
        }
    }

    private fun updateAlarmState(isAlarmOn: Boolean) {
        binding.ivBtnAlarmOption.setImageDrawable(
            if (isAlarmOn) {
                ContextCompat.getDrawable(this, R.drawable.ic_alarm_bell)
            } else {
                ContextCompat.getDrawable(this, R.drawable.ic_alarm_vibration)
            }
        )
    }

    private fun updateCounterViews(counters: List<Counter>) {
        if (binding.llTimers.childCount == 0) {
            repeat(counters.size) {
                val counterView = CounterView(this@TimerActivity).apply {
                    setOnClickListener { activeIndex ->
                        viewModel.setActiveCounter(activeIndex)
                    }
                }
                binding.llTimers.addView(counterView)
            }
        }
        counters.forEachIndexed { index, counter ->
            (binding.llTimers[index] as CounterView).updateCounter(counter)
        }
    }

    companion object {
        private const val KEY_CAPSULE_ID = "capsuleId"

        fun createTimerActivityIntent(
            callerActivity: Activity,
            capsuleId: Int
        ): Intent =
            Intent(callerActivity, TimerActivity::class.java).apply {
                putExtra(KEY_CAPSULE_ID, capsuleId)
            }
    }
}