package com.example.dgtimer.activities.timer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dgtimer.databinding.ActivityTimerBinding
import com.example.dgtimer.setAd
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KTimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private val viewModel: KTimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityTimerBinding.inflate(layoutInflater)

        setCapsuleData()
        initView()
        initObservers()
    }

    private fun setCapsuleData() {
        val capsuleId = intent.getIntExtra(KEY_CAPSULE_ID, -1)
        viewModel.setCapsuleData(capsuleId)
    }

    private fun initView() {
        val capsule = viewModel.capsule ?: return
        with(binding) {
            tvCapsuleName.text = capsule.name

            setAd(adView, this@KTimerActivity, lifecycle)
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
            }
        }
    }

    private fun updateCounterViews(counters: List<Counter>) {
        if (binding.llTimers.childCount == 0) {
            repeat(counters.size) {
                val counterView = KCounterView(this@KTimerActivity).apply {
                    setOnClickListener { activeIndex ->
                        viewModel.setActiveCounter(activeIndex)
                    }
                }
                binding.llTimers.addView(counterView)
            }
        }
        counters.forEachIndexed { index, counter ->
            (binding.llTimers[index] as KCounterView).updateCounter(counter)
        }
    }

    companion object {
        private const val KEY_CAPSULE_ID = "capsuleId"

        fun createTimerActivityIntent(
            callerActivity: Activity,
            capsuleId: Int
        ): Intent =
            Intent(callerActivity, KTimerActivity::class.java).apply {
                putExtra(KEY_CAPSULE_ID, capsuleId)
            }
    }
}