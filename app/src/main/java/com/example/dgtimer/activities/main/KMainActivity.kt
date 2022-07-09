package com.example.dgtimer.activities.main

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dgtimer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: KMainViewModel by viewModels()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initView()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.capsules.collect {
                        // todo update recyclerView
                    }
                }

                launch {
                    viewModel.searchedCapsules.collectLatest {
                        // todo update searched capsules recyclerView
                    }
                }
            }
        }
    }

    private fun initView() {
        with(binding) {
            fabUp.setOnClickListener { scrollUpToTop() }
            ivBtnSearch.setOnClickListener {
                // todo toggle searchBox
            }
            etSearch.doOnTextChanged { text, _, _, _ ->
                searchCapsules(text.toString())
            }
        }
    }

    private fun searchCapsules(text: String) {
        if (searchJob != null) return
        searchJob = lifecycleScope.launch {
            viewModel.searchCapsules(text)
            searchJob = null
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            val focusedView = currentFocus
            if (focusedView is EditText) {
                val searchRect = Rect()
                binding.llSearch.getGlobalVisibleRect(searchRect)
                if (!searchRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    focusedView.clearFocus()
                    hideKeyboard(focusedView)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun scrollUpToTop() {
        binding.rvCapsules.smoothScrollToPosition(0)
    }
}